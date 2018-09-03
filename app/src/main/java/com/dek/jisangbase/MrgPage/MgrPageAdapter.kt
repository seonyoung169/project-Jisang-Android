package com.dek.jisangbase.MrgPage

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.dek.jisangbase.GET.RegisteredItemListDataSub
import com.dek.jisangbase.R

/**
 * Created by 김진석 on 2018-01-08.
 */
class MgrPageAdapter(
        var MgrPageDataList: ArrayList<RegisteredItemListDataSub>,
                     var requestmanager : RequestManager?) : RecyclerView.Adapter<MgrPageViewHolder>(), View.OnClickListener {


    override fun onClick(v: View?) {
        when (v){

        }

    }

    private var onItemClick : View.OnClickListener? = null
    private var imgString : String? = null


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MgrPageViewHolder {
        val mainView : View = LayoutInflater.from(parent!!.context)
                .inflate(R.layout.mgr_page_items,parent,false)

        return MgrPageViewHolder(mainView)
    }

    override fun onBindViewHolder(holder: MgrPageViewHolder?, position: Int) {

        holder!!.itemNumber!!.text = MgrPageDataList!!.get(position).pcount.toString()
        holder!!.itemDate!!.text = MgrPageDataList!!.get(position).pdate

        //i는 리스트뷰에서의 index
        if(MgrPageDataList != null){
            Log.v("hell",MgrPageDataList.size.toString())
            Log.v("hell",MgrPageDataList!!.get(position).pimgs[0])
            for(i in 0..MgrPageDataList!!.size-1) {
                //사진 1개일때
                if(MgrPageDataList[i].pcount == 1) {
                    imgString = MgrPageDataList!!.get(position).pimgs[0]
                    requestmanager!!.load(imgString).into(holder!!.myPageItem1)
                }
                //사진 2개일때
                else if(MgrPageDataList[i].pcount == 2){
                    imgString = MgrPageDataList!!.get(position).pimgs[0]
                    requestmanager!!.load(imgString).into(holder!!.myPageItem1)

                    imgString = MgrPageDataList!!.get(position).pimgs[1]
                    requestmanager!!.load(imgString).into(holder!!.myPageItem2)

                }
                //사진 3개일때
                else if(MgrPageDataList[i].pcount == 3){
                    imgString = MgrPageDataList!!.get(position).pimgs[0]
                    requestmanager!!.load(imgString).into(holder!!.myPageItem1)

                    imgString = MgrPageDataList!!.get(position).pimgs[1]
                    requestmanager!!.load(imgString).into(holder!!.myPageItem2)

                    imgString = MgrPageDataList!!.get(position).pimgs[2]
                    requestmanager!!.load(imgString).into(holder!!.myPageItem3)

                }
                //4개이상일때
                else if(MgrPageDataList[i].pcount == 4){
                    imgString = MgrPageDataList!!.get(position).pimgs[0]
                    requestmanager!!.load(imgString).into(holder!!.myPageItem1)

                    imgString = MgrPageDataList!!.get(position).pimgs[1]
                    requestmanager!!.load(imgString).into(holder!!.myPageItem2)

                    imgString = MgrPageDataList!!.get(position).pimgs[2]
                    requestmanager!!.load(imgString).into(holder!!.myPageItem3)

//                    imgString = MgrPageDataList!!.get(position).pimgs[3]
//                    requestmanager!!.load(imgString).into(holder!!.myPageItem4)
                }
            }

            // > 버튼에 온클릭
            holder!!.nextButton!!.setOnClickListener(this)
        }


//        imgString = MgrPageDataList!!.get(position).pimgs[1]
//        MgrPageDataList!!.get(position).pimgs[2]
//        MgrPageDataList!!.get(position).pimgs[3]
//        MgrPageDataList!!.get(position).pimgs[4]


        /*holder!!.myPageItem1!!.setImageResource(MgrPageDataList!!.get(position).)
        holder!!.myPageItem2!!.setImageResource(MgrPageDataList!!.get(position).imgItem2)
        holder!!.myPageItem3!!.setImageResource(MgrPageDataList!!.get(position).imgItem3)*/
    }
    override fun getItemCount(): Int = MgrPageDataList!!.size

    fun setOnItemClickListener(l:View.OnClickListener){

    }
}