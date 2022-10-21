package com.example.management.adapter

import android.app.AlertDialog
import android.app.ProgressDialog.show
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.management.R
import com.example.management.model.requestItem
import kotlinx.coroutines.NonDisposableHandle.parent


class ItemViewAdapter(
    private val context: Context,
    private val dataset: MutableList<requestItem>,
) : RecyclerView.Adapter<ItemViewAdapter.ItemViewHolder>() {

    class ItemViewHolder (private val view: View) : RecyclerView.ViewHolder(view)
    {
        val name : TextView = view.findViewById(R.id.salad_description)
        val image: ImageView = view.findViewById((R.id.food))
        val type1: TextView  = view.findViewById((R.id.textView))
        val location: TextView  = view.findViewById((R.id.textView2))
        val details: Button = view.findViewById(R.id.details)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemLayout = LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
        return ItemViewHolder(itemLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]
        holder.name.text = item.name
        holder.image.setImageResource(item.ImageId)
        holder.type1.text = item.type1
        holder.location.text = item.location



        holder.details.setOnClickListener{
            val builder = AlertDialog.Builder(context)
            val binding1 = LayoutInflater.from(context).inflate(R.layout.dialogbox,null)
            val orderSummary ="*GARBAGE DISPOSAL ORDER*\n\n"+
            "*Details:*\n" +"1. Type: "+
                    item.type1+"\n"+"2. Nature: "+item.type2+"\n"+"3. Weight: "+item.weight+"\n"+"4. Date: "+item.date+"\n"+"5. Location: "+item.location
            with(builder){
                binding1.findViewById<ImageView>(R.id.wasteImage).setImageResource(item.ImageId)
                binding1.findViewById<TextView>(R.id.wastetype).text = item.type1
                binding1.findViewById<TextView>(R.id.wasteNature).text = item.type2
                binding1.findViewById<TextView>(R.id.wasteWeight).text = item.weight
                binding1.findViewById<TextView>(R.id.wasteDate).text = item.date
                binding1.findViewById<TextView>(R.id.wasteLocation).text = item.location
                binding1.findViewById<Button>(R.id.share).setOnClickListener{
                    val intent = Intent(Intent.ACTION_SEND)
                        .setType("text/plain")
                        .putExtra(Intent.EXTRA_SUBJECT,"")
                        .putExtra(Intent.EXTRA_TEXT, orderSummary)
                    intent.setPackage("com.whatsapp")
                    startActivity(context,intent,null)


                }
                setView(binding1)
                show()

            }

        }


    }


    override fun getItemCount(): Int {
        return dataset.size
    }
}