package com.watermelon.raftguide

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

data class RaftItem(val name:String,val english:String,val category:String,val use:String,val obtain:String,val recipe:String)

private val itemsData = listOf(
RaftItem("木板","Plank","基礎材料","建造木筏及製作設備的常用材料","海面漂流物、砍伐樹木","依遊戲內配方"),
RaftItem("塑膠","Plastic","基礎材料","製作工具與設備的常用材料","海面漂流物、桶子","依遊戲內配方"),
RaftItem("棕櫚葉","Palm Leaf","基礎材料","可製作繩子及多種物品","海面漂流物、棕櫚樹","製作繩子"),
RaftItem("繩子","Rope","加工材料","常見工具及設備製作材料","以棕櫚葉製作","棕櫚葉"),
RaftItem("廢料","Scrap","加工材料","製作金屬工具及設備零件","海面漂流物、島嶼水下","依遊戲內配方"),
RaftItem("石頭","Stone","基礎材料","用於工具及部分配方","海底採集、島嶼","依遊戲內配方"),
RaftItem("沙子","Sand","礦物材料","可在熔爐加工成玻璃","島嶼周圍海底","熔爐加工"),
RaftItem("黏土","Clay","礦物材料","可在熔爐加工成磚塊","島嶼周圍海底","熔爐加工"),
RaftItem("金屬礦","Metal Ore","礦物材料","冶煉後取得金屬錠","島嶼周圍海底","熔爐冶煉"),
RaftItem("銅礦","Copper Ore","礦物材料","冶煉後取得銅錠","島嶼周圍海底","熔爐冶煉"),
RaftItem("金屬錠","Metal Ingot","加工材料","高階工具及設備材料","熔爐冶煉金屬礦","金屬礦＋熔爐"),
RaftItem("銅錠","Copper Ingot","加工材料","電池及電子設備材料","熔爐冶煉銅礦","銅礦＋熔爐"),
RaftItem("玻璃","Glass","加工材料","用於部分設備及高階製作","熔爐冶煉沙子","沙子＋熔爐"),
RaftItem("斧頭","Axe","工具","砍伐樹木及拆除部分物件","工作台製作","依遊戲內配方"),
RaftItem("塑膠鉤","Plastic Hook","工具","勾取海面漂流資源","初始工具／製作","依遊戲內配方"),
RaftItem("魚竿","Fishing Rod","工具","釣魚取得食物","工作台製作","依遊戲內配方"),
RaftItem("研究桌","Research Table","設備","研究材料以解鎖配方","工作台製作","依遊戲內配方"),
RaftItem("簡易淨水器","Simple Purifier","飲水設備","將海水處理成飲用水","工作台製作","依遊戲內配方"),
RaftItem("烤架","Simple Grill","烹飪設備","烹煮魚類及其他食物","工作台製作","依遊戲內配方"),
RaftItem("小型儲物箱","Small Storage","儲存設備","存放材料與物品","工作台製作","依遊戲內配方"),
RaftItem("帆","Sail","航海設備","利用風向推動木筏","工作台製作","依遊戲內配方"),
RaftItem("引擎","Engine","航海設備","提供木筏動力","研究並製作","依遊戲內配方"),
RaftItem("接收器","Receiver","航海設備","配合天線追蹤故事地點訊號","研究並製作","依遊戲內配方"),
RaftItem("電池","Battery","電力設備","為電力設備供電","工作台製作","依遊戲內配方"),
RaftItem("馬鈴薯","Potato","食物","可直接食用或用於料理","漂流桶、種植","可烹煮"),
RaftItem("甜菜根","Beetroot","食物","可食用或用於料理","漂流桶、種植","可烹煮"),
RaftItem("鯖魚","Mackerel","食物","可在烤架上烹煮","釣魚","烤架"),
RaftItem("西瓜","Watermelon","食物","食用可補充口渴值","島嶼採集、種植","直接食用"),
RaftItem("棕櫚樹種子","Palm Seed","農業","種植後長成棕櫚樹","砍伐棕櫚樹","種植"),
RaftItem("蜂巢","Beehive","農業與養殖","設置後可取得蜂蜜相關資源","研究並製作","依遊戲內配方"),
RaftItem("蜂蜜","Honey","食物與材料","用於部分料理及製作","蜂巢收集","依遊戲內配方")
)

class MainActivity:ComponentActivity(){
 override fun onCreate(savedInstanceState:Bundle?){super.onCreate(savedInstanceState);setContent{GuideScreen()}}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GuideScreen(){
 var mode by remember{mutableStateOf("一般模式")}
 var query by remember{mutableStateOf("")}
 var category by remember{mutableStateOf("全部")}
 var chosen by remember{mutableStateOf<RaftItem?>(null)}
 var favorites by remember{mutableStateOf(setOf<String>())}
 val categories=listOf("全部")+itemsData.map{it.category}.distinct()
 val shown=itemsData.filter{(category=="全部"||it.category==category)&&(query.isBlank()||it.name.contains(query,true)||it.english.contains(query,true))}
 MaterialTheme(colorScheme=darkColorScheme(primary=androidx.compose.ui.graphics.Color(0xFF55C98B),surface=androidx.compose.ui.graphics.Color(0xFF192723))){
 Scaffold(topBar={TopAppBar(title={Text("RAFT GUIDE",fontWeight=FontWeight.Bold)})}){ pad->
 Column(Modifier.fillMaxSize().padding(pad).padding(14.dp)){
 Text("木筏求生・繁體中文百科")
 Row(horizontalArrangement=Arrangement.spacedBy(8.dp)){
 FilterChip(selected=mode=="一般模式",onClick={mode="一般模式";chosen=null},label={Text("一般模式")})
 FilterChip(selected=mode=="創造模式",onClick={mode="創造模式";chosen=null},label={Text("創造模式")})
 }
 Text("目前模式："+mode,style=MaterialTheme.typography.bodySmall)
 OutlinedTextField(query,{query=it},Modifier.fillMaxWidth(),label={Text("搜尋中文或英文物品")},singleLine=true)
 if(chosen==null){
 androidx.compose.foundation.lazy.LazyRow(horizontalArrangement=Arrangement.spacedBy(5.dp)){items(categories){ c->FilterChip(selected=category==c,onClick={category=c},label={Text(c)})}}
 Text("初始物品資料："+shown.size+" 項",color=MaterialTheme.colorScheme.primary)
 LazyColumn(verticalArrangement=Arrangement.spacedBy(7.dp)){
 items(shown){ item->Card(Modifier.fillMaxWidth().clickable{chosen=item}){
 Row(Modifier.padding(14.dp),horizontalArrangement=Arrangement.SpaceBetween){
 Column(Modifier.weight(1f)){Text(item.name,fontWeight=FontWeight.Bold);Text(item.english+" · "+item.category,style=MaterialTheme.typography.bodySmall)}
 Text(if(item.name in favorites)"★" else "☆",Modifier.clickable{favorites=if(item.name in favorites)favorites-item.name else favorites+item.name}.padding(8.dp))
 }
 }}
 }
 }else{
 TextButton(onClick={chosen=null}){Text("← 返回列表")}
 Card(Modifier.fillMaxWidth()){Column(Modifier.padding(16.dp),verticalArrangement=Arrangement.spacedBy(9.dp)){
 Text(chosen!!.name,style=MaterialTheme.typography.headlineMedium,fontWeight=FontWeight.Bold)
 Text(chosen!!.english,color=MaterialTheme.colorScheme.primary)
 Text("分類："+chosen!!.category)
 HorizontalDivider()
 Text("物品用途",fontWeight=FontWeight.Bold);Text(chosen!!.use)
 Text("取得方式",fontWeight=FontWeight.Bold);Text(chosen!!.obtain)
 Text("製作材料／配方",fontWeight=FontWeight.Bold);Text(chosen!!.recipe)
 Text("提醒：目前為起始資料，完整物品與正式配方仍需逐項核對。",style=MaterialTheme.typography.bodySmall)
 Button(onClick={favorites=if(chosen!!.name in favorites)favorites-chosen!!.name else favorites+chosen!!.name}){Text(if(chosen!!.name in favorites)"取消收藏" else "加入收藏")}
 }}
 }
 }
 }
 }
}
