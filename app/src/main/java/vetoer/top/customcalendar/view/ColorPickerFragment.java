package vetoer.top.customcalendar.view;

import android.app.Dialog;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import vetoer.top.customcalendar.R;

/**
 * Created by tg on 18-1-31.
 */

public class ColorPickerFragment extends DialogFragment {

    ColorDialogInterface mListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.color_pick)
                .setItems(R.array.color_array, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // 0:G,1:Y,2:R:3..
                        mListener.onColorClick(ColorPickerFragment.this,i);
                    }
                })
                .create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            mListener = (ColorDialogInterface) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString());
        }
    }

    public interface ColorDialogInterface extends DialogInterface{
        public void onColorClick(DialogFragment dialog,int color);
    }

}
