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

public class clickCardAdapter extends RecyclerView.Adapter<clickCardAdapter.ViewHolder> {

    private List<Card> clickCards;

    public clickCardAdapter(List<Card> clickCards){
        this.clickCards = clickCards;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.form,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        // 监听事件，点击移除卡牌
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = holder.getAdapterPosition();  //获取点击的卡牌位置
                Card card = clickCards.remove(pos);
                notifyDataSetChanged();

                Toast.makeText(view.getContext(), "you remove " + card.getKind() + card.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Card card = clickCards.get(position);
        holder.cardForm.setImageResource(card.getImageId());
    }

    @Override
    public int getItemCount() {
        return clickCards.size();
    }

    static public class ViewHolder extends RecyclerView.ViewHolder {
        //卡牌图片格式
        ImageView cardForm;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardForm = itemView.findViewById(R.id.cardForm);
        }
    }
}
