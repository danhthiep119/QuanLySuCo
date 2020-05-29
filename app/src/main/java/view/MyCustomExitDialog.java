package view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;

import com.example.quanlysucotruncu.R;

public class MyCustomExitDialog extends Dialog {
    ImageButton btnExit,btnContinue;
    Activity mContext;

    public MyCustomExitDialog(Activity mContext) {
        super(mContext);
        this.mContext = mContext;
        setContentView(R.layout.custom_exit_dialog);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.finish();
            }
        });
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    private void addControls() {
        btnExit=findViewById(R.id.btnExit);
        btnContinue=findViewById(R.id.btnContinue);
    }
}
