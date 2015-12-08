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
import edu.chc.appdev.glennmatthys.exam.TextValidators.HostIdExists;
import edu.chc.appdev.glennmatthys.exam.TextValidators.NotEmpty;

public class AddMeetingActivity extends AppCompatActivity
{
    private FormValidator validator;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);

        this.validator = new FormValidator();
        this.validator.addField(
            new EditTextValidator(
                (EditText) this.findViewById(R.id.etName),
                new ITextValidator[]{new NotEmpty()}
            )
        );
        this.validator.addField(
            new EditTextValidator(
                (EditText) this.findViewById(R.id.etDescription),
                new ITextValidator[]{new NotEmpty()}
            )
        );
        this.validator.addField(
            new EditTextValidator(
                (EditText) this.findViewById(R.id.etLocation),
                new ITextValidator[]{new NotEmpty()}
            )
        );
        this.validator.addField(
            new EditTextValidator(
                (EditText) this.findViewById(R.id.etDate),
                new ITextValidator[]{new NotEmpty()}
            )
        );
        this.validator.addField(
            new EditTextValidator(
                (EditText) this.findViewById(R.id.etTime),
                new ITextValidator[]{new NotEmpty()}
            )
        );
        this.validator.addField(
            new EditTextValidator(
                (EditText) this.findViewById(R.id.etHostId),
                new ITextValidator[]{new NotEmpty(), new HostIdExists(DB.getInstance(this))}
            )
        );
    }

    public void addMeetingToDB(View view)
    {
        if (!this.validator.isValid())
        {
            (Toast.makeText(this, "Form contains errors", Toast.LENGTH_LONG)).show();
            return;
        }

        DB db = DB.getInstance(this);
        try
        {
            db.addMeeting(
                ((EditText) this.findViewById(R.id.etName)).getText().toString(),
                ((EditText) this.findViewById(R.id.etDescription)).getText().toString(),
                ((EditText) this.findViewById(R.id.etLocation)).getText().toString(),
                ((EditText) this.findViewById(R.id.etDate)).getText().toString(),
                ((EditText) this.findViewById(R.id.etTime)).getText().toString(),
                Integer.parseInt(
                    ((EditText) this.findViewById(R.id.etHostId)).getText().toString()
                )
            );

            (Toast.makeText(this, "Meeting added!", Toast.LENGTH_LONG)).show();

            this.setResult(Activity.RESULT_OK);
            this.finish();
        }
        catch (Exception ex)
        {
            (Toast.makeText(this, "Failed adding: " + ex.getMessage(), Toast.LENGTH_LONG)).show();
        }

    }
}
