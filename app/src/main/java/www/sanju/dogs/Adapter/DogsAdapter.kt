package www.sanju.dogs.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.dogs_rv_layout.view.*
import www.sanju.dogs.Model.DogApi
import www.sanju.dogs.R

class DogsAdapter (val context: Context?, private val images: ArrayList<DogApi>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {


        holder.itemView.dogImage.clipToOutline = true
        Picasso.get().load(images[position].message).into(holder.itemView.dogImage)


        //Image Animation
        //  holder.newsImageUrl.animation = AnimationUtils.loadAnimation(context,R.anim.fade_image)


        //Card Animation
        // holder.card.animation = AnimationUtils.loadAnimation(context,R.anim.card_fade)    }



    }





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.dogs_rv_layout, parent, false)
        return ViewHolder(v)


    }

    override fun getItemCount(): Int {
        return images.size
    }

}

class ViewHolder(v: View?) : RecyclerView.ViewHolder(v!!),View.OnClickListener {
    override fun onClick(v: View?) {



    }

    init {
        itemView.setOnClickListener(this)
    }


    val dogsImages = itemView.dogImage!!



}