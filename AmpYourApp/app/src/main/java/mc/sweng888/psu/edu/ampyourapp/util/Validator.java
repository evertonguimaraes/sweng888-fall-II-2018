package mc.sweng888.psu.edu.ampyourapp.util;

import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import mc.sweng888.psu.edu.ampyourapp.R;

public class Validator {

    private static boolean validateEmail(EditText view){
        String input = view.getText().toString();
        return (TextUtils.isEmpty(input) && Patterns.EMAIL_ADDRESS.matcher(input).matches()) ? true : false;
    }


}
