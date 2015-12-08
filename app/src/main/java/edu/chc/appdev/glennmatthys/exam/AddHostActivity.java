package edu.chc.appdev.glennmatthys.exam;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import edu.chc.appdev.glennmatthys.exam.FormValidator.EditTextValidator;
import edu.chc.appdev.glennmatthys.exam.FormValidator.FormValidator;
import edu.chc.appdev.glennmatthys.exam.FormValidator.ITextValidator;
import edu.chc.appdev.glennmatthys.exam.TextValidators.NotEmpty;

public class AddHostActivity extends AppCompatActivity
{
    private FormValidator validator;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_host);

        this.validator = new FormValidator();
        this.validator.addField(
            new EditTextValidator(
                (EditText) this.findViewById(R.id.etEmailAddress),
                new ITextValidator[]{new NotEmpty()}
            )
        );
    }

    public void addHostToDB(View view)
    {
        if (!this.validator.isValid())
        {
            (Toast.makeText(this, "Form contains errors", Toast.LENGTH_LONG)).show();
            return;
        }

        DB db = DB.getInstance(this);

        EditText etEmailAddress = (EditText) this.findViewById(R.id.etEmailAddress);

        db.addHost(etEmailAddress.getText().toString());

        (Toast.makeText(this, "Host added!", Toast.LENGTH_LONG)).show();

        this.setResult(Activity.RESULT_OK);
        this.finish();
    }

}
