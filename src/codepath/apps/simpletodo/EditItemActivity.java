package codepath.apps.simpletodo;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class EditItemActivity extends Activity {
private EditText multiLineNewtextItem;
private String position;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String text = getIntent().getStringExtra("text");
		 position = getIntent().getStringExtra("position");
		setContentView(R.layout.activity_edit_item);
		multiLineNewtextItem = (EditText) findViewById(R.id.mleditnewText);
		multiLineNewtextItem.setText(text);
		multiLineNewtextItem.setSelection(multiLineNewtextItem.getText().length());
	}
	
	public void onSaveItem(View v){
		EditText etEditedItem = (EditText) findViewById(R.id.mleditnewText);
		Intent data = new Intent();
		data.putExtra("name", etEditedItem.getText().toString());
		data.putExtra("position", position);
		setResult(RESULT_OK,data);
    	this.finish();
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_item, menu);
		return true;
	}

}
