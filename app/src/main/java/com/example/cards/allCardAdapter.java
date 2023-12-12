package com.example.cards;

import android.animation.ObjectAnimator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class allCardAdapter extends RecyclerView.Adapter<allCardAdapter.ViewHolder> {
    private final clickCardAdapter clickAdapter;
    private final List<Card> clickCards;
    private List<Card> allCards;

    public allCardAdapter(clickCardAdapter clickAdapter, List<Card> clickCards, List<Card> allCards) {
        this.clickAdapter = clickAdapter;
        this.clickCards = clickCards;
        this.allCards = allCards;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.form, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        //  点击卡牌，添加到待计算的卡牌中
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int pos = holder.getAdapterPosition();
                Card card = allCards.get(pos);
                if(clickCards.contains(card)){
                    Toast.makeText(view.getContext(), "这张" +card.getKind()+ card.getName()+"已经被选择了", Toast.LENGTH_SHORT).show();
                }
                else if (clickCards.size()<4) {
                    clickCards.add(card);
                    clickAdapter.notifyDataSetChanged();  //刷新选牌区
                    Toast.makeText(view.getContext(), "你选择了" +card.getKind()+ card.getName(), Toast.LENGTH_SHORT).show();
                    ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationX",0,100f);
                    animator.setDuration(2000);
                    animator.start();
                }else{
                    Toast.makeText(view.getContext(), "已经选了四张牌了", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Card card = allCards.get(position);
        holder.cardForm.setImageResource(card.getImageId());
    }

    @Override
    public int getItemCount() {
        return allCards.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //卡牌图片格式
        ImageView cardForm;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardForm = itemView.findViewById(R.id.cardForm);
        }
    }
}
