package com.example.cards;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Card> allCards = new ArrayList<>();  //所有卡牌
    private  List<Card> clickCards = new ArrayList<>();  //点击的卡牌
    Button clearBt;
    Button startBt;
    TextView resultTitle;
    TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initCards();  //初始化
        //生成四张计算牌界面
        final RecyclerView clickCardsView = findViewById(R.id.clickCards);
        GridLayoutManager lMClickCArds = new GridLayoutManager(this, 4);  //每行四张牌
        clickCardsView.setLayoutManager(lMClickCArds);
        //生成所有牌界面
        final RecyclerView allCardsView = findViewById(R.id.allCards);
        GridLayoutManager lMallCards = new GridLayoutManager(this, 13);  //每行13张牌
//        FrameLayout lMallCards = new FrameLayout(this);
        allCardsView.setLayoutManager(lMallCards);

        final clickCardAdapter clickAdapter = new clickCardAdapter(clickCards);
        clickCardsView.setAdapter(clickAdapter);
        final allCardAdapter allCardsAdapter = new allCardAdapter(clickAdapter,clickCards,allCards);
        allCardsView.setAdapter(allCardsAdapter);

        allCardsView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                return false;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                Toast.makeText(MainActivity.this, "触摸事件", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
        //  绑定按钮
        clearBt = findViewById(R.id.clearBt);
        startBt = findViewById(R.id.startBt);
        resultText = findViewById(R.id.resultText);
        resultTitle = findViewById(R.id.resultTitle);
        //  开始计算的监听事件
        startBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("开始计算");
                if (clickCards.size() < 4) {
                    resultText.setText("你选的牌不足四张。");
                } else {
                      //计算24点
                    Integer[] calCards = new Integer[4];
                    for (int i = 0; i < clickCards.size(); i++) {
                        calCards[i] = Integer.parseInt(clickCards.get(i).getName());
                    }
                    String calText = Calculation24.calculate(calCards);
                    resultText.setText(calText);
//                    Compute24Poker test = new Compute24Poker();  //24点逻辑类
//                    test.compute(cards4);  //24点计算
//                    List<String> results = test.getResults();  //获取计算结果
//                    String mline = "";
//                    for (String s : results) {
//                        mline += s + "\n";
//                    }
//                    resultText.setText(mline);
                }
            }
        });
        // 重新选择的监听事件
        clearBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("重新选择");
                clickCards.clear();
                resultText.setText("");
                clickAdapter.notifyDataSetChanged();
                allCardsAdapter.notifyDataSetChanged();
            }
        });

    }

    //  初始化选牌界面
    private void initCards() {

        for (int i = 1; i <= 13; i++) {
            Integer sId;
            if(i<10){
                sId = getResource("card0"+Integer.toString(i));  //获取图片ID
            }else{
                sId = getResource("card"+Integer.toString(i));  //获取图片ID
            }
            Card c = new Card("" + i, sId, "黑桃");
            allCards.add(c);
        }
        for (int i = 1; i <= 13; i++) {
            Integer sId;
            sId = getResource("card"+Integer.toString(i+13));  //获取图片ID
            Card c = new Card("" + i, sId, "红桃");
            allCards.add(c);

        }
        for (int i = 1; i <= 13; i++) {
            Integer sId;
            sId = getResource("card"+Integer.toString(i+26));  //获取图片ID
            Card c = new Card("" + i, sId, "梅花");
            allCards.add(c);

        }
        for (int i = 1; i <= 13; i++) {
            Integer sId;
            sId = getResource("card"+Integer.toString(i+39));  //获取图片ID
            Card c = new Card("" + i, sId, "方块");
            allCards.add(c);

        }
    }

    //根据图片名字获取资源ID
    public int  getResource(String imageName){
        Class mipmap = R.drawable.class;
        try {
            Field field =  mipmap.getField(imageName);
            int resId = field.getInt(imageName);
            return resId;
        } catch (NoSuchFieldException e) {
            //如果没有在"drawable"下找到imageName,将会返回0
            return 0;
        } catch (IllegalAccessException e) {
            return 0;
        }
    }
}