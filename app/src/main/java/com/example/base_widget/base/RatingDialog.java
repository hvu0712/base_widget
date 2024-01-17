package com.example.base_widget.base;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.base_widget.R;


public class RatingDialog extends Dialog {
    private OnPress onPress;
    private TextView tvTitle, tvContent;
    private RatingBar rtb;
    private ImageView imgIcon, imageView;
    private EditText editFeedback;
    private Context context;
    private Button btnRate, btnLater;

    public RatingDialog(Context context2) {
        super(context2, R.style.BaseDialog);
        this.context = context2;
        setContentView(R.layout.dialog_rating_app);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.width = WindowManager.LayoutParams.MATCH_PARENT;
        attributes.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(attributes);
        getWindow().setSoftInputMode(16);
        tvTitle = findViewById(R.id.tvTitle);
        tvContent = findViewById(R.id.tvContent);
        rtb = findViewById(R.id.rtb);
        imgIcon = findViewById(R.id.imgIcon);
        imageView = findViewById(R.id.imageView);
        editFeedback = findViewById(R.id.editFeedback);
        btnRate = findViewById(R.id.btnRate);
        btnLater = findViewById(R.id.btnLater);
        onclick();
        changeRating();
        rtb.setRating(5f);
    }

    public interface OnPress {
        void send();

        void rating();

        void cancel();

        void later();
        void dismissDialog();
    }

    public void init(Context context, OnPress onPress) {
        this.onPress = onPress;
    }

    public void changeRating() {
        rtb.setOnRatingBarChangeListener((ratingBar, rating, fromUser) -> {
            String getRating = String.valueOf(rtb.getRating());
            switch (getRating) {
                case "1.0":
                    editFeedback.setVisibility(View.GONE);
                    btnRate.setText(context.getResources().getString(R.string.send));
                    imgIcon.setImageResource(R.drawable.rating_1);
                    tvTitle.setText(context.getResources().getString(R.string.oh_no));
                    tvContent.setText(context.getResources().getString(R.string.please_leave_us_some_feedback));
                    break;
                case "2.0":
                    editFeedback.setVisibility(View.GONE);
                    btnRate.setText(context.getResources().getString(R.string.send));
                    imgIcon.setImageResource(R.drawable.rating_2);
                    tvTitle.setText(context.getResources().getString(R.string.oh_no));
                    tvContent.setText(context.getResources().getString(R.string.please_leave_us_some_feedback));
                    break;
                case "3.0":
                    editFeedback.setVisibility(View.GONE);
                    btnRate.setText(context.getResources().getString(R.string.send));
                    imgIcon.setImageResource(R.drawable.rating_3);
                    tvTitle.setText(context.getResources().getString(R.string.oh_no));
                    tvContent.setText(context.getResources().getString(R.string.please_leave_us_some_feedback));
                    break;
                case "4.0":
                    editFeedback.setVisibility(View.GONE);
                    btnRate.setText(context.getResources().getString(R.string.rate));
                    imgIcon.setImageResource(R.drawable.rating_4);
                    tvTitle.setText(context.getResources().getString(R.string.we_like_you_too));
                        tvContent.setText(context.getResources().getString(R.string.thank_for_your_feedback));
                    break;
                case "5.0":
                    editFeedback.setVisibility(View.GONE);
                    btnRate.setText(context.getResources().getString(R.string.rate));
                    imgIcon.setImageResource(R.drawable.rating_5);
                    tvTitle.setText(context.getResources().getString(R.string.we_like_you_too));
                    tvContent.setText(context.getResources().getString(R.string.thank_for_your_feedback));
                    break;
                default:
                    btnRate.setText(context.getResources().getString(R.string.rate));
                    editFeedback.setVisibility(View.GONE);
                    imgIcon.setImageResource(R.drawable.rating_0);
                    tvTitle.setText(context.getResources().getString(R.string.we_are_working));
                    tvContent.setText(context.getResources().getString(R.string.we_greatly_appreciate));
                    break;
            }
        });


    }

    public String getNewName() {
        return this.editFeedback.getText().toString();
    }

    public String getRating() {
        return String.valueOf(this.rtb.getRating());
    }

    public void onclick() {
        btnRate.setOnClickListener(view -> {
            if (rtb.getRating() == 0) {
                Toast.makeText(context, context.getResources().getString(R.string.please_feedback), Toast.LENGTH_SHORT).show();
                return;
            }
            if (rtb.getRating() <= 4.0) {
                imageView.setVisibility(View.GONE);
                imgIcon.setVisibility(View.GONE);
                onPress.send();
            } else {
                imageView.setVisibility(View.VISIBLE);
                imgIcon.setVisibility(View.VISIBLE);
                onPress.rating();
            }
        });
        btnLater.setOnClickListener(view -> onPress.later());

    }

    @Override
    public void dismiss() {
        super.dismiss();
        onPress.dismissDialog();
    }

    public void showDialog() {
        show();
        rtb.setRating(5f);
    }

}

