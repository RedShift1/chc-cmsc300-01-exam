package edu.chc.appdev.glennmatthys.exam.TextValidators;

import edu.chc.appdev.glennmatthys.exam.DB;
import edu.chc.appdev.glennmatthys.exam.FormValidator.ITextValidator;

/**
 * Created by Glenn on 8/12/2015.
 */
public class HostIdExists implements ITextValidator
{
    private DB db;
    public HostIdExists(DB db)
    {
        this.db = db;
    }

    @Override
    public Boolean isValid(String text)
    {
        try
        {
            int num = Integer.parseInt(text);
            return db.doesHostExist(num);
        }
        catch (Exception ex)
        {
            return false;
        }
    }

    @Override
    public String getReason()
    {
        return "Host ID does not exist";
    }
}
