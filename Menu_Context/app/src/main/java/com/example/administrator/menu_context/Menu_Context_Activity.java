package com.example.administrator.menu_context;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class Menu_Context_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__context_);

        TextView tv = new TextView(this);
        tv.setText("上下文菜单的载体");
        registerForContextMenu(tv);
        setContentView(tv);
    }
    @Override
    public boolean onContextItemSelected(MenuItem item){

        super.onContextItemSelected(item);
        switch (item.getItemId()){
            case 1://做新建的事情
                break;
            case 2://做打开的事情
                break;
        }
        return super.onContextItemSelected(item);
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){

        menu.add(0, 1, 1,"新建");
        menu.add(0, 2, 2,"打开");
        super.onCreateContextMenu(menu,v,menuInfo);
    }
}
