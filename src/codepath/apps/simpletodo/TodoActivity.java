package codepath.apps.simpletodo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class TodoActivity extends Activity {
	//ArrayList<String> items;
	private ArrayList<String> todoItems;
	private ArrayAdapter<String> todoAdapter;
	private ListView lvItems;
	private EditText etNewItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        etNewItem = (EditText) findViewById(R.id.etNewItem);
        lvItems = (ListView) findViewById(R.id.ivItems);
        readItems();
        todoAdapter = new ArrayAdapter<String>(getBaseContext(),
        		android.R.layout.simple_list_item_1, todoItems);
        lvItems.setAdapter(todoAdapter);
        setupListViewListener();
        
    }
    
    private void setupListViewListener() {
		// TODO Auto-generated method stub
		lvItems.setOnItemLongClickListener(new OnItemLongClickListener(){

			@Override
			public boolean onItemLongClick(AdapterView<?> adapter, View item,
					int pos, long id) {
				// TODO Auto-generated method stub
				todoItems.remove(pos);
				todoAdapter.notifyDataSetChanged();
				writeItems();
				return true;
			}
			
		});
		
		///// adding the code for one click open new activity 
		lvItems.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> adapter, View item, int pos,
					long id) {
				// TODO Auto-generated method stub
				/*String textInItem = ((TextView)item).getText().toString();
				Toast.makeText(getBaseContext(), textInItem, Toast.LENGTH_LONG).show();*/
				launchEditItemView(item, pos);
			
			}
			
		});
		
		/////end of changes 
	}
    
    

	
    
    public void launchEditItemView(View item, int pos) {
		// TODO Auto-generated method stub
		Intent i = new Intent(TodoActivity.this, EditItemActivity.class);
		String textInItem = ((TextView)item).getText().toString();
		i.putExtra("text", textInItem);
		//i.putExtra("position",pos);
		startActivity(i);
	}

	public void onAddedItem(View v){
    	String itemText = etNewItem.getText().toString();
    	todoAdapter.add(itemText);
    	etNewItem.setText("");
    	writeItems();
    }
    
    private void readItems() {
    	File fileDir = getFilesDir();
    	File todofile = new File(fileDir, "todo.txt");
    	try {
    		todoItems = new ArrayList<String>(FileUtils.readLines(todofile));
    	}catch(IOException e){
    		todoItems = new ArrayList<String>();
    	}
    }
    
    private void writeItems() {
    	File fileDir = getFilesDir();
    	File todoFile = new File(fileDir, "todo.txt");
    	try {
    		FileUtils.writeLines(todoFile,todoItems);
    	}catch (IOException e){
    		e.printStackTrace();
    	}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.todo, menu);
        return true;
    }
    
}
