package com.example.assignment3_tenzintselha;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class Fragment1 extends Fragment {
    View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_1, container, false); //display the layout
        TextView questionText = (TextView)view.findViewById(R.id.questionText); //connection
        questionText.setText(getArguments().getInt("question")); //get the element from the database from the mainactivity using bundle
        questionText.setBackgroundResource(getArguments().getInt("color"));
        return view;
    }
    public static Fragment1 newInstance(int questionId, int color) { //this newinstance is created every time we click true or false button
        Fragment1 fragment = new Fragment1();
        Bundle bundle = new Bundle();
        bundle.putInt("question",questionId);
        bundle.putInt("color",color);
        fragment.setArguments(bundle);
        return fragment;
    }

}


//shuffle called when save and ignore button clicked
//create a shuffle method in questionbank

/* work on fragment - create the algorithm step
call the frgament manage with getsupport
begin the transaction
inside the transaction,
add the the fragement
commit

//fragmentlayoutclass extend fragment{
/*bundle bundle = new bundle
bundle.putint("",questionid)
bundle.putint(""",colorid)



oncreatemethod{
inflater.inflate(r.layout.fragmentxml)
textview.settext(getintent("questionid")
textview.setbackgroundcolor(getintent(colorid)*\
 */


/*storage manager:
savedata() {
fileoutputsystem fos = null; //to save and reset data
fos.openoutput(filename)
fos.write("total/totalattempt",getbytes)
maketoast(mesg = "save data to " + )
getfiledir()...).show()


}

loaddata(){ //when you click on the menu bar
fileinputstream fileinput = null
stringbuilder //need the string builder to read the file one by one
user stringbuilder method append
store your content in a new variable then pass that variable to your alert dialog box(which will have the result)

}

resetData()
 */

/*questionindex = question.size();
update your preogress bar

showdialog(){
builder.message(total score is score).setPositiveButton(onclicklistener on save button)
create a storemanager object.savedata(total number of question, correct answers)

 */

/*
create a menu folder under your resource file, create a menu
mainactivity:
create oncreatemenu method
 */