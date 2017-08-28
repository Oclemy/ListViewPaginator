package com.tutorials.hp.listviewpaginator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
/*
- Our MainActivity class.
- Derives from AppCompatActivity.
- The views we use are ListView and buttons.
- By default current page is set to 0, which is actually our first page.
- So when we run the project, the first page data is generated, with next button enabled while previous button disabled.
- We can then navigate to next page, then to previous or next pages until the last page. When we reach last page, the next button gets disabled.
- Methods: onCreate(),toggleButtons().
 */
public class MainActivity extends AppCompatActivity {
    /*
    DECLARATIONS
     */
    ListView lv;
    Button nextBtn, prevBtn;
    Paginator p = new Paginator();
    private int totalPages = Paginator.TOTAL_NUM_ITEMS / Paginator.ITEMS_PER_PAGE;
    private int currentPage = 0;

    /*
    WHEN ACTIVITY IS CREATED.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //INIATILZE VIEW.
        lv = (ListView) findViewById(R.id.lv);
        nextBtn = (Button) findViewById(R.id.nextBtn);
        prevBtn = (Button) findViewById(R.id.prevBtn);
        prevBtn.setEnabled(false);

        //SHOW TOAST WHEN LISTVIEW ITEM IS CLICKED
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, p.generatePage(currentPage).get(i), Toast.LENGTH_SHORT).show();
            }
        });

        //SET ADAPTER TO LISTVIEW
        lv.setAdapter(new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, p.generatePage(currentPage)));

        //NAVIGATE TO NEXT PAGE.
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentPage += 1;
                // enableDisableButtons();
                lv.setAdapter(new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, p.generatePage(currentPage)));
                toggleButtons();
            }
        });

        //NAVIGATE TO PREVIOUS PAGE
        prevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentPage -= 1;
                lv.setAdapter(new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, p.generatePage(currentPage)));
                toggleButtons();
            }
        });
    }
    /*
    TOGGLE NEXT AND PREVIOUS BUTTONS DEPENDING ON CURRENT PAGE
     */
    private void toggleButtons() {
        if (currentPage == totalPages) {
            nextBtn.setEnabled(false);
            prevBtn.setEnabled(true);
        } else if (currentPage == 0) {
            prevBtn.setEnabled(false);
            nextBtn.setEnabled(true);
        } else if (currentPage >= 1 && currentPage <= 5) {
            nextBtn.setEnabled(true);
            prevBtn.setEnabled(true);
        }
    }
}
