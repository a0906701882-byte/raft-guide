package com.watermelon.raftguide

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

data class RaftItem(val name:String,val en:String,val cat:String,val icon:String,val use:String,val get:String,val recipe:String,val creativeOnly:Boolean=false)
private val data = listOf(
RaftItem("木板","Plank","基礎材料","🪵","建造木筏、設備及作為燃料。","海面漂流物、桶子、木箱。","直接使用於建築與多種配方。"),
RaftItem("塑膠","Plastic","基礎材料","🧴","製作工具、設備與建築。","海面漂流物、桶子、木箱。","多數配方直接使用。"),
RaftItem("棕櫚葉","Palm Leaf","基礎材料","🌿","製作繩子、帆與設備。","海面漂流物、棕櫚樹。","4 片棕櫚葉可製作 1 條繩子。"),
RaftItem("繩子","Rope","加工材料","🧵","常見工具與設備材料。","以棕櫚葉製作。","4 棕櫚葉 → 1 繩子。"),
RaftItem("廢料","Scrap","基礎材料","🔩","製作金屬設備與零件。","島嶼周圍海底、木箱。","依研究解鎖配方使用。"),
RaftItem("石頭","Stone","礦物材料","🪨","工具與部分設備材料。","島嶼周圍海底。","依研究解鎖配方使用。"),
RaftItem("沙子","Sand","礦物材料","🏖️","製作玻璃、乾燥磚。","島嶼周圍海底。","熔爐加工可製成玻璃。"),
RaftItem("黏土","Clay","礦物材料","🟤","製作乾燥磚與黏土碗。","島嶼周圍海底。","與沙子製作濕磚，放置晾乾。"),
RaftItem("金屬礦","Metal Ore","礦物材料","⛏️","製作金屬錠。","島嶼周圍海底。","熔爐冶煉 → 金屬錠。"),
RaftItem("銅礦","Copper Ore","礦物材料","🟠","製作銅錠與電子設備材料。","島嶼周圍海底。","熔爐冶煉 → 銅錠。"),
RaftItem("鈦礦","Titanium Ore","礦物材料","💎","後期高階設備材料。","故事地點、探索地點與交易站。","熔爐加工成鈦錠。"),
RaftItem("海藻","Seaweed","海洋材料","🌱","製作藤蔓黏液等材料。","島嶼附近水下採集。","放入熔爐加工。"),
RaftItem("藤蔓黏液","Vine Goo","加工材料","🫧","製作瓶子及電子零件等。","海藻經熔爐加工。","海藻 + 熔爐。"),
RaftItem("金屬錠","Metal Ingot","加工材料","🧱","高階設備與建築零件材料。","金屬礦放入熔爐。","金屬礦 + 熔爐。"),
RaftItem("銅錠","Copper Ingot","加工材料","🟠","電池、電路板與電子設備材料。","銅礦放入熔爐。","銅礦 + 熔爐。"),
RaftItem("玻璃","Glass","加工材料","🪟","製作部分高階設備。","沙子放入熔爐。","沙子 + 熔爐。"),
RaftItem("乾燥磚","Dry Brick","加工材料","🧱","建造熔爐等工作設備。","黏土與沙子製成濕磚後晾乾。","濕磚放在木筏上等待乾燥。"),
RaftItem("螺栓","Bolt","加工材料","🔩","製作部分工具與設備。","研究解鎖後製作。","研究桌解鎖配方。"),
RaftItem("鉸鏈","Hinge","加工材料","⚙️","製作儲存與機械設備。","研究解鎖後製作。","研究桌解鎖配方。"),
RaftItem("釘子","Nail","加工材料","📌","製作建築與設備。","研究解鎖後製作。","研究桌解鎖配方。"),
RaftItem("羽毛","Feather","動物材料","🪶","製作部分裝備與裝飾。","收集海鷗掉落物。","依研究配方使用。"),
RaftItem("皮革","Leather","動物材料","🟫","製作背包與裝備。","取得生皮後加工。","研究桌解鎖配方。"),
RaftItem("羊毛","Wool","動物材料","☁️","製作背包與裝備。","飼養羊駝並收集。","研究桌解鎖配方。"),
RaftItem("蜂蜜","Honey","食物與材料","🍯","料理及加工材料。","蜂箱收集。","用於部分料理與加工配方。"),
RaftItem("蜂巢","Honeycomb","動物材料","🍯","製作蜂蜜相關設備與材料。","有蜜蜂的島嶼附近採集。","依研究配方使用。"),
RaftItem("馬鈴薯","Potato","食物","🥔","直接食用或用於料理。","桶子、木箱、種植。","烤架烹煮或料理鍋配方。"),
RaftItem("甜菜根","Beetroot","食物","🫜","直接食用或用於料理。","桶子、木箱、種植。","烤架烹煮或料理鍋配方。"),
RaftItem("椰子","Coconut","食物","🥥","補充食物與水分。","島嶼棕櫚樹。","可直接食用或用於料理。"),
RaftItem("西瓜","Watermelon","食物","🍉","補充食物與水分。","島嶼採集、種植。","可直接食用。"),
RaftItem("芒果","Mango","食物","🥭","補充食物與水分。","島嶼採集、種植。","可直接食用或用於果汁。"),
RaftItem("鳳梨","Pineapple","食物","🍍","補充食物與水分。","島嶼採集、種植。","可直接食用或用於果汁。"),
RaftItem("生魚","Raw Fish","食物","🐟","烹煮後補充食物。","使用魚竿釣魚。","烤架烹煮；部分種類可用於料理鍋。"),
RaftItem("雞蛋","Egg","食物與材料","🥚","食用或製作料理。","飼養雞並收集。","可用於料理鍋配方。"),
RaftItem("牛奶","Milk","食物","🥛","飲用或作為料理材料。","以水桶向山羊收集。","可用於料理鍋配方。"),
RaftItem("空杯子","Empty Cup","容器","🥤","裝取飲用水。","製作選單製作。","塑膠等材料；以遊戲配方為準。"),
RaftItem("空瓶子","Empty Bottle","容器","🧴","裝取較多飲用水。","製作選單製作。","塑膠、藤蔓黏液等；以遊戲配方為準。"),
RaftItem("簡易淨水器","Simple Purifier","飲水設備","💧","將海水處理成淡水。","製作選單製作。","木板、棕櫚葉、塑膠等；依遊戲配方。"),
RaftItem("進階淨水器","Advanced Purifier","飲水設備","🚰","處理多份海水，不需燃料。","研究解鎖後製作。","需玻璃等進階材料；依遊戲配方。"),
RaftItem("簡易烤架","Simple Grill","烹飪設備","🔥","烹煮魚類與蔬菜。","製作選單製作。","木板、廢料等；依遊戲配方。"),
RaftItem("料理鍋","Cooking Pot","烹飪設備","🍲","依食譜製作料理。","研究解鎖後製作。","需金屬錠等進階材料。"),
RaftItem("榨汁機","Juicer","烹飪設備","🧃","依果汁食譜製作飲品。","研究解鎖後製作。","需電路板等進階材料。"),
RaftItem("收集網","Collection Net","木筏設備","🕸️","自動收集漂流經過的物資。","研究解鎖後製作，安裝於木筏邊緣。","需木板、繩子等；依遊戲配方。"),
RaftItem("研究桌","Research Table","研究設備","📚","研究材料並解鎖製作項目。","製作選單製作。","木板、廢料等；依遊戲配方。"),
RaftItem("熔爐","Smelter","加工設備","♨️","加工礦物與材料。","研究解鎖後製作。","需乾燥磚等材料。"),
RaftItem("帆","Sail","航海設備","⛵","利用風向推動木筏。","製作選單製作。","木板、棕櫚葉與繩子等。"),
RaftItem("引擎","Engine","航海設備","🚤","提供木筏動力。","研究解鎖後製作。","金屬錠、鉸鏈與電路板等。"),
RaftItem("方向盤","Steering Wheel","航海設備","🧭","控制木筏航行方向。","研究解鎖後製作。","金屬錠、鉸鏈等。"),
RaftItem("接收器","Receiver","導航設備","📡","顯示故事地點與訊號方向。","研究解鎖後製作。","需要電路板等材料。"),
RaftItem("天線","Antenna","導航設備","📶","與接收器搭配定位訊號。","研究解鎖後製作。","需要電路板等材料。"),
RaftItem("電池","Battery","電力設備","🔋","供應接收器等設備電力。","研究解鎖後製作。","銅錠、塑膠等材料。"),
RaftItem("小型儲物箱","Small Storage","儲存設備","📦","存放材料與物品。","製作選單製作。","依遊戲內配方製作。"),
RaftItem("大型儲物箱","Large Storage","儲存設備","🗄️","提供較大的物品存放空間。","研究解鎖後製作。","需鉸鏈等進階材料。"),
RaftItem("小型種植箱","Small Crop Plot","農業設備","🪴","種植小型作物。","製作選單製作。","依遊戲內配方製作。"),
RaftItem("中型種植箱","Medium Crop Plot","農業設備","🌱","種植中型作物。","製作選單製作。","依遊戲內配方製作。"),
RaftItem("大型種植箱","Large Crop Plot","農業設備","🌴","種植樹木。","製作選單製作。","依遊戲內配方製作。"),
RaftItem("灑水器","Sprinkler","農業設備","💦","自動替種植區澆水。","研究解鎖後製作。","需要電池及進階材料。"),
RaftItem("草地種植區","Grass Plot","農業設備","🌾","種植草供飼養動物食用。","製作選單製作。","依遊戲內配方製作。"),
RaftItem("稻草人","Scarecrow","農業設備","🎃","減少海鷗對作物的損害。","製作選單製作。","依遊戲內配方製作。"),
RaftItem("建築錘","Building Hammer","工具","🔨","建造、擴建與修理木筏。","初期製作。","依遊戲內配方製作。"),
RaftItem("魚竿","Fishing Rod","工具","🎣","釣取魚類作為食物。","製作選單製作。","木製與金屬版本配方不同。"),
RaftItem("剪刀","Shears","工具","✂️","替羊駝收集羊毛。","研究解鎖後製作。","需金屬錠等材料。"),
RaftItem("鏟子","Shovel","探索工具","🥄","挖掘島嶼上的泥土與埋藏物。","研究解鎖後製作。","需金屬錠等材料。"),
RaftItem("金屬探測器","Metal Detector","探索工具","📟","尋找島嶼上的埋藏物品。","研究解鎖後製作。","需電池等材料，搭配鏟子使用。"),
RaftItem("錨","Anchor","航海設備","⚓","讓木筏停留在島嶼附近。","製作選單製作。","有一次性與可重複使用款式。"),
RaftItem("蜂箱","Beehive","農業設備","🐝","放置於木筏上收集蜂蜜。","研究解鎖後製作。","需蜂巢等材料，附近需有花朵。"),
RaftItem("生質燃料","Biofuel","燃料","🛢️","供應引擎等設備使用。","生質燃料精煉器加工。","使用食物等材料加工。"),
RaftItem("空水壺","Empty Canteen","容器","🫗","攜帶較多飲用水。","製作選單製作。","依遊戲內配方表。"),
RaftItem("黏土碗","Clay Bowl","容器","🥣","盛裝料理鍋製作的食物。","製作選單製作。","使用黏土等材料；依遊戲內配方表。"),
RaftItem("飲料杯","Drinking Glass","容器","🥛","盛裝榨汁機製作的飲品。","製作選單製作。","使用玻璃；依遊戲內配方表。"),
RaftItem("水桶","Bucket","容器","🪣","從山羊收集牛奶。","製作選單製作。","使用金屬等材料；依遊戲內配方表。"),
RaftItem("進階大型種植箱","Advanced Large Crop Plot","農業設備","🌳","種植樹木並改善種植效率。","研究解鎖或取得藍圖後製作。","依遊戲內配方表。"),
RaftItem("進階中型種植箱","Advanced Medium Crop Plot","農業設備","🌱","種植中型作物。","研究解鎖或取得藍圖後製作。","依遊戲內配方表。"),
RaftItem("進階小型種植箱","Advanced Small Crop Plot","農業設備","🪴","種植小型作物。","研究解鎖或取得藍圖後製作。","依遊戲內配方表。"),
RaftItem("進階稻草人","Advanced Scarecrow","農業設備","🎃","提供較好的作物保護。","研究解鎖或取得藍圖後製作。","依遊戲內配方表。"),
RaftItem("電動烤架","Electric Grill","烹飪設備","⚡","使用電力烹煮食物。","取得藍圖並研究後製作。","依遊戲內配方表。"),
RaftItem("電動淨水器","Electric Purifier","飲水設備","🚰","使用電力處理飲用水。","取得藍圖並研究後製作。","依遊戲內配方表。"),
RaftItem("冰箱","Fridge","儲存設備","🧊","保存食物。","研究解鎖或取得藍圖後製作。","依遊戲內配方表。"),
RaftItem("回收機","Recycler","加工設備","♻️","將資源轉換為交易用方塊。","取得藍圖並研究後製作。","投入可回收材料運作。"),
RaftItem("生質燃料精煉器","Biofuel Refiner","加工設備","🛢️","將有機材料加工成生質燃料。","研究解鎖後製作。","依遊戲內配方表。"),
RaftItem("進階生質燃料精煉器","Advanced Biofuel Refiner","加工設備","⚙️","更有效率地製作生質燃料。","取得藍圖並研究後製作。","依遊戲內配方表。"),
RaftItem("電動熔爐","Electric Smelter","加工設備","⚡","使用電力冶煉材料。","取得藍圖並研究後製作。","依遊戲內配方表。"),
RaftItem("電池充電器","Battery Charger","電力設備","🔋","為可充電電池補充電力。","取得藍圖並研究後製作。","依遊戲內配方表。"),
RaftItem("風力發電機","Wind Turbine","電力設備","🌬️","利用風力供應電力。","取得藍圖並研究後製作。","依遊戲內配方表。"),
RaftItem("水箱","Water Tank","儲水設備","🚿","儲存並分配淡水。","取得藍圖並研究後製作。","需搭配水管等設備。"),
RaftItem("水管","Water Pipe","管線設備","〰️","連接淨水與儲水設備。","取得藍圖並研究後製作。","依遊戲內配方表。"),
RaftItem("燃料管","Fuel Pipe","管線設備","〰️","將燃料輸送至引擎等設備。","取得藍圖並研究後製作。","依遊戲內配方表。"),
RaftItem("燃料槽","Fuel Tank","燃料設備","🛢️","儲存生質燃料。","取得藍圖並研究後製作。","依遊戲內配方表。"),
RaftItem("進階收集網","Advanced Collection Net","木筏設備","🕸️","升級收集漂流物的能力。","取得藍圖並研究後製作。","依遊戲內配方表。"),
RaftItem("固定式錨","Stationary Anchor","航海設備","⚓","可重複操作以固定木筏。","研究解鎖後製作。","依遊戲內配方表。"),
RaftItem("進階固定式錨","Advanced Stationary Anchor","航海設備","⚓","升級固定木筏的錨定設備。","取得藍圖並研究後製作。","依遊戲內配方表。"),
RaftItem("引擎控制器","Engine Controls","航海設備","🎛️","集中控制木筏引擎。","取得藍圖並研究後製作。","需搭配引擎使用。"),
RaftItem("槳","Paddle","航海設備","🚣","手動推動木筏。","製作選單製作。","依遊戲內配方表。"),
RaftItem("風向旗","Streamer","航海設備","🎏","顯示風向。","製作選單製作。","依遊戲內配方表。"),
RaftItem("滑索","Zipline","交通設備","🚡","連接不同高度或區域。","取得藍圖並研究後製作。","需搭配滑索工具與滑索零件。"),
RaftItem("滑索工具","Zipline Tool","工具","🧰","沿滑索移動。","取得藍圖並研究後製作。","依遊戲內配方表。"),
RaftItem("大型背包","Big Backpack","裝備","🎒","增加攜帶物品容量。","取得藍圖並研究後製作。","依遊戲內配方表。"),
RaftItem("頭燈","Head Light","裝備","🔦","在黑暗區域照明。","研究解鎖後製作。","依遊戲內配方表。"),
RaftItem("進階頭燈","Advanced Head Light","裝備","💡","提供更好的照明。","取得藍圖並研究後製作。","依遊戲內配方表。"),
RaftItem("望遠鏡","Binoculars","工具","🔭","觀察遠處地形與目標。","製作選單製作。","依遊戲內配方表。"),
RaftItem("掃網","Sweep Net","工具","🪲","捕捉島嶼上的昆蟲。","製作選單製作。","依遊戲內配方表。"),
RaftItem("濕磚","Wet Brick","加工材料","🧱","晾乾後製成乾燥磚。","以沙子與黏土製作。","濕磚放置在木筏上晾乾。"),
RaftItem("銅","Copper","礦物材料","🟠","冶煉成銅錠。","島嶼附近海底採集銅礦。","熔爐冶煉。"),
RaftItem("鈦錠","Titanium Ingot","加工材料","💎","製作高階設備與工具。","鈦礦放入熔爐。","鈦礦 + 熔爐。"),
RaftItem("電路板","Circuit Board","加工材料","🧩","製作導航、電力與高階設備。","研究解鎖後製作。","需銅錠、藤蔓黏液等材料；依遊戲配方。"),
RaftItem("蜂蜜罐","Bee Jar","材料","🍯","收集蜂蜜相關資源。","捕捉蜜蜂取得。","用於蜂蜜相關遊戲配方。"),
RaftItem("巨型蛤蜊","Giant Clam","海洋材料","🐚","研究材料與裝飾用途。","島嶼附近海底採集。","投入研究桌解鎖相關項目。"),
RaftItem("泥土","Dirt","地面材料","🟤","建立草地種植區。","使用鏟子在大型島嶼挖掘。","用於草地種植區等配方。"),
RaftItem("花朵","Flowers","植物材料","🌼","染料、裝飾與蜜蜂採集用途。","島嶼採集或種植。","可放入油漆磨坊製作顏料。"),
RaftItem("顏料","Paint","裝飾材料","🎨","替木筏建築上色。","花朵放入油漆磨坊。","花朵 + 油漆磨坊。"),
RaftItem("油漆刷","Paint Brush","裝飾工具","🖌️","替建築表面上色。","製作選單製作。","依遊戲內配方表。"),
RaftItem("油漆磨坊","Paint Mill","裝飾設備","🎨","將花朵加工成顏料。","製作選單製作。","依遊戲內配方表。"),
RaftItem("床","Simple Bed","家具","🛏️","休息並設定重生點。","製作選單製作。","依遊戲內配方表。"),
RaftItem("吊床","Hammock","家具","🛌","休息並設定重生點。","研究解鎖後製作。","依遊戲內配方表。"),
RaftItem("桌子","Table","家具","🪑","放置裝飾與物品。","製作選單或裝飾包取得。","依遊戲內配方表。"),
RaftItem("椅子","Chair","家具","🪑","木筏室內裝飾。","製作選單或裝飾包取得。","依遊戲內配方表。"),
RaftItem("架子","Shelf","家具","🗄️","放置裝飾與收納。","製作選單或裝飾包取得。","依遊戲內配方表。"),
RaftItem("燈籠","Lantern","照明設備","🏮","照亮木筏區域。","製作選單或探索取得。","依遊戲內配方表。"),
RaftItem("時鐘","Clock","裝飾","🕒","顯示遊戲時間。","裝飾包或製作選單取得。","依遊戲內配方表。"),
RaftItem("日曆","Calendar","裝飾","📅","木筏裝飾。","裝飾包或製作選單取得。","依遊戲內配方表。"),
RaftItem("地毯","Rug","裝飾","🧶","木筏室內裝飾。","裝飾包取得。","依遊戲內配方表。"),
RaftItem("告示牌","Sign","裝飾","🪧","標記區域與提醒事項。","製作選單取得。","依遊戲內配方表。"),
RaftItem("獎盃展示板","Trophy Board","裝飾","🏆","展示探索或成就紀念品。","裝飾包或探索取得。","依遊戲內配方表。"),
RaftItem("鳥巢","Birds Nest","動物設備","🪺","吸引海鷗停留並收集羽毛。","製作選單製作。","依遊戲內配方表。"),
RaftItem("進階烤架","Advanced Grill","烹飪設備","🔥","一次烹煮較多食物。","研究解鎖後製作。","依遊戲內配方表。"),
RaftItem("儲物櫃","Storage","儲存設備","🗄️","收納木筏物品。","製作選單解鎖。","依遊戲內配方表。"),
RaftItem("垃圾桶","Trashcan","木筏設備","🗑️","整理不需要的物品。","製作選單或研究解鎖。","依遊戲內配方表。"),
RaftItem("沙發","Sofa","家具","🛋️","木筏室內裝飾。","裝飾包取得。","依遊戲內配方表。"),
RaftItem("櫥櫃","Cupboard","家具","🚪","木筏室內裝飾與收納。","裝飾包取得。","依遊戲內配方表。"),
RaftItem("細節木板","Detail Plank","裝飾","🪵","建築細節裝飾。","藍圖或裝飾包取得。","依遊戲內配方表。"),
RaftItem("金屬燈籠","Metal Lantern","照明設備","🏮","照亮木筏區域。","裝飾包或探索取得。","依遊戲內配方表。"),
RaftItem("火盆","Fire Basket","裝飾","🔥","木筏裝飾照明。","裝飾包取得。","依遊戲內配方表。"),
RaftItem("壁爐","Fireplace","裝飾","🪵","室內裝飾。","裝飾包取得。","依遊戲內配方表。"),
RaftItem("畫作","Painting","裝飾","🖼️","裝飾木筏牆面。","探索或裝飾包取得。","依遊戲內配方表。"),
RaftItem("勵志語錄","Motivational Quote","裝飾","💬","牆面文字裝飾。","裝飾包取得。","依遊戲內配方表。"),
RaftItem("書堆","Bookpile","裝飾","📚","室內裝飾。","裝飾包取得。","依遊戲內配方表。"),
RaftItem("打開的書","Open Book","裝飾","📖","室內桌面裝飾。","裝飾包取得。","依遊戲內配方表。"),
RaftItem("紙張組","Paper Bundle","裝飾","📄","室內裝飾。","裝飾包取得。","依遊戲內配方表。"),
RaftItem("燭台","Candlestick","照明設備","🕯️","室內裝飾照明。","裝飾包取得。","依遊戲內配方表。"),
RaftItem("串燈","String Light","照明設備","✨","木筏裝飾照明。","裝飾包取得。","依遊戲內配方表。"),
RaftItem("餐具","Cutlery","裝飾","🍴","餐桌裝飾。","裝飾包取得。","依遊戲內配方表。"),
RaftItem("馬克杯","Mug","裝飾","☕","桌面裝飾。","裝飾包取得。","依遊戲內配方表。"),
RaftItem("旗幟","Flag","裝飾","🚩","木筏外觀裝飾。","裝飾包取得。","依遊戲內配方表。"),
RaftItem("羽毛筆","Quill","裝飾","🪶","桌面裝飾。","裝飾包取得。","依遊戲內配方表。"),
RaftItem("地球儀","World Globe","裝飾","🌍","室內裝飾。","裝飾包取得。","依遊戲內配方表。"),
RaftItem("窗簾","Curtain","裝飾","🪟","室內裝飾。","裝飾包取得。","依遊戲內配方表。"),
RaftItem("戶外廁所","Outhouse","裝飾","🚻","木筏裝飾。","裝飾包取得。","依遊戲內配方表。"),
RaftItem("木製浴缸","Wooden Bathtub","裝飾","🛁","木筏裝飾。","裝飾包取得。","依遊戲內配方表。"),
RaftItem("柴火架","Firewood Rack","裝飾","🪵","木筏裝飾。","裝飾包取得。","依遊戲內配方表。"),
RaftItem("背包","Backpack","裝備","🎒","增加攜帶容量。","研究解鎖後製作。","依遊戲內配方表。"),
RaftItem("蛙鞋","Flippers","裝備","🤿","提升水下移動便利性。","研究解鎖後製作。","依遊戲內配方表。"),
RaftItem("氧氣瓶","Oxygen Bottle","裝備","🫧","延長水下探索時間。","研究解鎖後製作。","依遊戲內配方表。"),
RaftItem("船長帽","Captain’s Hat","裝備","🧢","角色外觀收藏。","探索取得。","不可製作。"),
RaftItem("飛行員頭盔","Pilot Helmet","裝備","🪖","角色外觀收藏。","探索取得。","不可製作。"),
RaftItem("洞穴蘑菇","Cave Mushroom","食物","🍄","可作為料理材料。","探索洞穴採集。","依食譜卡片使用。"),
RaftItem("紅莓","Red Berries","食物","🍒","可食用或作為料理材料。","森林島嶼採集。","依食譜卡片使用。"),
RaftItem("銀藻","Silver Algae","食物材料","🌿","料理材料。","水下探索採集。","依食譜卡片使用。"),
RaftItem("桶裝牛奶","Bucket of Milk","食物","🥛","飲用或料理材料。","以水桶向山羊收集。","依食譜卡片使用。"),
RaftItem("生鯰魚","Raw Catfish","食物","🐟","烹煮後食用。","釣魚取得。","烤架或料理鍋。"),
RaftItem("熟鯰魚","Cooked Catfish","食物","🍽️","可直接食用。","烹煮生鯰魚。","烤架。"),
RaftItem("生鯡魚","Raw Herring","食物","🐟","烹煮或料理材料。","釣魚取得。","烤架或料理鍋。"),
RaftItem("熟鯡魚","Cooked Herring","食物","🍽️","可直接食用。","烹煮生鯡魚。","烤架。"),
RaftItem("生鯖魚","Raw Mackerel","食物","🐟","烹煮或料理材料。","釣魚取得。","烤架或料理鍋。"),
RaftItem("熟鯖魚","Cooked Mackerel","食物","🍽️","可直接食用。","烹煮生鯖魚。","烤架。"),
RaftItem("生鮭魚","Raw Salmon","食物","🐟","烹煮或料理材料。","釣魚取得。","烤架或料理鍋。"),
RaftItem("熟鮭魚","Cooked Salmon","食物","🍽️","可直接食用。","烹煮生鮭魚。","烤架。"),
RaftItem("生羅非魚","Raw Tilapia","食物","🐟","烹煮或料理材料。","釣魚取得。","烤架或料理鍋。"),
RaftItem("熟羅非魚","Cooked Tilapia","食物","🍽️","可直接食用。","烹煮生羅非魚。","烤架。"),
RaftItem("生鯧魚","Raw Pomfret","食物","🐟","烹煮或料理材料。","釣魚取得。","烤架或料理鍋。"),
RaftItem("熟鯧魚","Cooked Pomfret","食物","🍽️","可直接食用。","烹煮生鯧魚。","烤架。"),
RaftItem("生雞腿","Raw Drumstick","食物","🍗","料理材料。","取得食材。","依食譜卡片使用。"),
RaftItem("熟雞腿","Cooked Drumstick","食物","🍗","可直接食用。","烹煮生雞腿。","烤架。"),
RaftItem("熟肉","Cooked Meat","食物","🍖","可直接食用。","烹煮肉類食材。","烤架。"),
RaftItem("熟甜菜根","Cooked Beet","食物","🫜","可直接食用。","烹煮生甜菜根。","烤架。"),
RaftItem("熟馬鈴薯","Cooked Potato","食物","🥔","可直接食用。","烹煮生馬鈴薯。","烤架。"),
RaftItem("熟鯊魚肉","Cooked Shark Meat","食物","🍖","遊戲內熟食項目。","烹煮後取得。","本指南不提供戰鬥或捕獵教學。"),
RaftItem("蕉種子","Banana Seed","創造模式雜項","🌱","種植香蕉植株。","創造模式物品清單。","種植於大型種植箱。",true),
RaftItem("棕櫚樹種子","Palm Seed","創造模式雜項","🌴","種植棕櫚樹。","創造模式物品清單。","種植於大型種植箱。",true),
RaftItem("芒果種子","Mango Seed","創造模式雜項","🥭","種植芒果樹。","創造模式物品清單。","種植於大型種植箱。",true),
RaftItem("松果","Pinecone","創造模式雜項","🌲","種植松樹。","創造模式物品清單。","種植於大型種植箱。",true),
RaftItem("樺樹種子","Birch Seed","創造模式雜項","🌳","種植樺樹。","創造模式物品清單。","種植於大型種植箱。",true),
RaftItem("西瓜種子","Watermelon Seed","創造模式雜項","🍉","種植西瓜。","創造模式物品清單。","種植於中型種植箱。",true),
RaftItem("鳳梨種子","Pineapple Seed","創造模式雜項","🍍","種植鳳梨。","創造模式物品清單。","種植於中型種植箱。",true),
RaftItem("草莓種子","Strawberry Seed","創造模式雜項","🍓","種植草莓。","創造模式物品清單。","種植於小型種植箱。",true),
RaftItem("黑色花種子","Black Seed","創造模式雜項","🌱","種植黑色花朵。","創造模式物品清單。","種植於小型種植箱。",true),
RaftItem("藍色花種子","Blue Seed","創造模式雜項","🌱","種植藍色花朵。","創造模式物品清單。","種植於小型種植箱。",true),
RaftItem("紅色花種子","Red Seed","創造模式雜項","🌱","種植紅色花朵。","創造模式物品清單。","種植於小型種植箱。",true),
RaftItem("白色花種子","White Seed","創造模式雜項","🌱","種植白色花朵。","創造模式物品清單。","種植於小型種植箱。",true),
RaftItem("黃色花種子","Yellow Seed","創造模式雜項","🌱","種植黃色花朵。","創造模式物品清單。","種植於小型種植箱。",true),
RaftItem("唐加羅瓦植物","Tangaroa Plant","創造模式雜項","🪴","故事地點主題裝飾植物。","創造模式物品清單。","裝飾用途。",true),
RaftItem("幸運貓","Lucky Cat","創造模式雜項","🐱","收藏裝飾。","創造模式物品清單。","裝飾用途。",true),
RaftItem("主要湯姆模型","Major Tom","創造模式雜項","🚀","收藏裝飾。","創造模式物品清單。","裝飾用途。",true),
RaftItem("玩具機器人","Toy Robot","創造模式雜項","🤖","收藏裝飾。","創造模式物品清單。","裝飾用途。",true),
RaftItem("黃金玩具機器人","Golden Toy Robot","創造模式雜項","🤖","收藏裝飾。","創造模式物品清單。","裝飾用途。",true),
RaftItem("提基零件","Tiki Piece","創造模式雜項","🗿","收集組合型裝飾。","探索與創造模式物品清單。","收藏用途。",true),
RaftItem("舊鞋","Old Shoe","創造模式雜項","👞","趣味收集物。","創造模式物品清單。","可作為裝飾或種植用途。",true),
RaftItem("收音機","Radio","創造模式雜項","📻","播放遊戲音樂。","創造模式物品清單。","裝飾與音樂用途。",true),
RaftItem("鋼琴","Piano","創造模式雜項","🎹","可演奏音樂。","創造模式物品清單。","互動裝飾。",true),
RaftItem("錄音帶","Cassette Tapes","創造模式雜項","📼","音樂收藏。","探索或創造模式物品清單。","收藏用途。",true),
)
private val islands = listOf(
"小島" to "採集樹木、椰子與其他植物；在島嶼周圍海底尋找礦物、沙子與黏土。",
"大型島嶼" to "探索高處、洞穴與海岸，留意作物、特殊材料及交易站。",
"無線電塔" to "依接收器訊號尋找故事地點，探索建築並閱讀筆記。",
"瓦薩加坦" to "故事探索地點之一，仔細搜尋房間、線索與筆記。",
"巴爾博亞島" to "森林型故事島，探索步道與研究設施。",
"商隊小鎮" to "探索水下與聚落設施，可找到故事線索及特殊物資。",
"唐加羅瓦" to "大型故事地點，探索建築、地下區域與城市設施。",
"瓦魯納角" to "故事章節地點，探索水下建築與周邊區域。",
"坦佩雷斯" to "冰雪地區故事地點，探索設施並收集故事線索。",
"烏托邦" to "故事後期地點，依線索逐步探索各區域。"
)


private val animalInfo = listOf(
"羊駝 Llama" to "使用草地種植區提供草料並保持圍欄安全；可使用剪刀收集羊毛。",
"山羊 Goat" to "飼養在木筏圍欄內，使用水桶收集牛奶；記得準備草料。",
"雞 Clucker" to "飼養在木筏圍欄內，可收集雞蛋；需要草地供應食物。",
"蜜蜂 Bee" to "在適合的島嶼區域可捕捉，蜂箱周圍需有花朵才能生產蜂蜜。",
"海鷗 Seagull" to "可能停留在鳥巢附近；留意種植區作物的保護。"
)
private val recipeInfo = listOf(
"燒烤拼盤 BBQ" to "料理鍋料理。依遊戲內食譜卡片確認食材與份量。",
"鯰魚豪華餐 Catfish Deluxe" to "料理鍋料理。依食譜卡片準備鯰魚與指定配料。",
"椰子雞 Coconut Chicken" to "料理鍋料理。依食譜卡片準備椰子與指定配料。",
"果醬雞腿 Drumstick with Jam" to "料理鍋料理。依食譜卡片準備雞腿與指定配料。",
"魚湯 Fish Stew" to "料理鍋料理。依食譜卡片準備魚類與指定配料。",
"水果什錦 Fruit Compot" to "料理鍋料理。依食譜卡片準備水果。",
"頭骨濃湯 Head Broth" to "料理鍋料理。依食譜卡片準備指定食材。",
"豐盛燉菜 Hearty Stew" to "料理鍋料理。依食譜卡片準備指定食材。",
"蘑菇煎蛋 Mushroom Omelette" to "料理鍋料理。依食譜卡片準備蘑菇與蛋。",
"鮭魚沙拉 Salmon Salad" to "料理鍋料理。依食譜卡片準備鮭魚與蔬菜。",
"鯊魚晚餐 Shark Dinner" to "料理鍋料理。依食譜卡片確認指定食材。",
"簡易魚湯 Simple Fish Stew" to "料理鍋料理。依食譜卡片準備魚類與蔬菜。",
"果醬牛排 Steak with Jam" to "料理鍋料理。依食譜卡片準備指定食材。",
"壽司 Sushi" to "料理鍋料理。依食譜卡片準備魚類與配料。",
"蔬菜湯 Vegetable Soup" to "料理鍋料理。依食譜卡片準備蔬菜。",
"簡易冰沙 Simple Smoothie" to "榨汁機飲品。依食譜卡片準備指定水果。",
"椰子甜菜飲 Coconut Beat" to "榨汁機飲品。依食譜卡片準備椰子與甜菜根。",
"芒果香蕉汁 Mangonana" to "榨汁機飲品。依食譜卡片準備水果。",
"紅色西瓜汁 Red Melon" to "榨汁機飲品。依食譜卡片準備西瓜與指定配料。",
"甜菜根特調 Redbeet Shot" to "榨汁機飲品。依食譜卡片準備甜菜根。",
"銀色冰沙 Silver Smoothie" to "榨汁機飲品。依食譜卡片準備指定材料。",
"香辣鳳梨莓果飲 Spicy Pineberry" to "榨汁機飲品。依食譜卡片準備指定材料。",
"草莓可樂達 Strawberry Colada" to "榨汁機飲品。依食譜卡片準備草莓與指定材料。"
)

class MainActivity:ComponentActivity(){
 override fun onCreate(savedInstanceState:Bundle?){super.onCreate(savedInstanceState);setContent{GuideApp()}}
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GuideApp(){
 var mode by remember{mutableStateOf("一般模式")}
 var page by remember{mutableStateOf("物品百科")}
 var query by remember{mutableStateOf("")}
 var category by remember{mutableStateOf("全部")}
 var chosen by remember{mutableStateOf<RaftItem?>(null)}
 var favorites by remember{mutableStateOf(setOf<String>())}
 val categories=listOf("全部")+data.map{it.cat}.distinct()
 val filtered=data.filter{(mode=="創造模式"||!it.creativeOnly)&&(category=="全部"||it.cat==category)&&(query.isBlank()||it.name.contains(query,true)||it.en.contains(query,true)||it.use.contains(query,true))}
 MaterialTheme(colorScheme=darkColorScheme(primary=Color(0xFF54D69A),secondary=Color(0xFF82C9B0),surface=Color(0xFF1B2925),background=Color(0xFF101A18))){
 Scaffold(topBar={TopAppBar(title={Text("RAFT 指南",fontWeight=FontWeight.Black)})},
 bottomBar={NavigationBar{listOf("物品百科","收藏","料理","動物","新手指南","島嶼").forEach{p->NavigationBarItem(selected=page==p,onClick={page=p;chosen=null},icon={Text(when(p){"物品百科"->"📚";"收藏"->"⭐";"料理"->"🍲";"動物"->"🐾";"新手指南"->"🛟";else->"🏝️"})},label={Text(p)})}}}){
 pad->Column(Modifier.fillMaxSize().padding(pad).padding(horizontal=14.dp)){
 Text("木筏求生・繁體中文離線指南",style=MaterialTheme.typography.titleMedium)
 if(page=="物品百科"||page=="收藏"){
 Row(horizontalArrangement=Arrangement.spacedBy(8.dp)){
 FilterChip(selected=mode=="一般模式",onClick={mode="一般模式";chosen=null},label={Text("一般模式")})
 FilterChip(selected=mode=="創造模式",onClick={mode="創造模式";chosen=null},label={Text("創造模式")})
 }
 OutlinedTextField(query,{query=it},Modifier.fillMaxWidth(),label={Text("搜尋中文、英文或用途")},singleLine=true)
 if(page=="物品百科")LazyRow(horizontalArrangement=Arrangement.spacedBy(5.dp)){items(categories){c->FilterChip(selected=category==c,onClick={category=c},label={Text(c)})}}
 if(chosen==null){
 val list=if(page=="收藏")data.filter{it.name in favorites&&(query.isBlank()||it.name.contains(query,true)||it.en.contains(query,true))}else filtered
 Text("目前顯示 "+list.size+" 項",color=MaterialTheme.colorScheme.primary)
 LazyColumn(verticalArrangement=Arrangement.spacedBy(7.dp),contentPadding=PaddingValues(bottom=12.dp)){
 items(list){item->Card(Modifier.fillMaxWidth().clickable{chosen=item}){
 Row(Modifier.padding(13.dp),horizontalArrangement=Arrangement.spacedBy(12.dp)){
 Text(item.icon,style=MaterialTheme.typography.headlineMedium)
 Column(Modifier.weight(1f)){Text(item.name,fontWeight=FontWeight.Bold);Text(item.en+" · "+item.cat,style=MaterialTheme.typography.bodySmall)}
 TextButton(onClick={favorites=if(item.name in favorites)favorites-item.name else favorites+item.name}){Text(if(item.name in favorites)"★" else "☆")}
 }
 }}
 }
 }else{
 TextButton(onClick={chosen=null}){Text("← 返回列表")}
 Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()),verticalArrangement=Arrangement.spacedBy(10.dp)){
 Card(Modifier.fillMaxWidth()){Column(Modifier.padding(18.dp),verticalArrangement=Arrangement.spacedBy(9.dp)){
 Text(chosen!!.icon,style=MaterialTheme.typography.displayMedium)
 Text(chosen!!.name,style=MaterialTheme.typography.headlineMedium,fontWeight=FontWeight.Bold)
 Text(chosen!!.en,color=MaterialTheme.colorScheme.primary)
 Text("分類："+chosen!!.cat)
 HorizontalDivider()
 Text("物品用途",fontWeight=FontWeight.Bold);Text(chosen!!.use)
 Text("取得方式",fontWeight=FontWeight.Bold);Text(chosen!!.get)
 Text("製作材料／配方",fontWeight=FontWeight.Bold);Text(chosen!!.recipe)
 Text("提醒：部分配方需先在研究桌解鎖；版本與進度可能影響配方，請以遊戲內配方表為準。",style=MaterialTheme.typography.bodySmall)
 Button(onClick={favorites=if(chosen!!.name in favorites)favorites-chosen!!.name else favorites+chosen!!.name}){Text(if(chosen!!.name in favorites)"取消收藏" else "加入收藏")}
 }}
 }
 }
 }else if(page=="料理"){
 LazyColumn(verticalArrangement=Arrangement.spacedBy(9.dp),contentPadding=PaddingValues(bottom=12.dp)){
 items(recipeInfo){entry->Card(Modifier.fillMaxWidth()){Column(Modifier.padding(15.dp),verticalArrangement=Arrangement.spacedBy(5.dp)){Text("🍲 "+entry.first,fontWeight=FontWeight.Bold);Text(entry.second)}}}
 }
 }else if(page=="動物"){
 LazyColumn(verticalArrangement=Arrangement.spacedBy(9.dp),contentPadding=PaddingValues(bottom=12.dp)){
 items(animalInfo){entry->Card(Modifier.fillMaxWidth()){Column(Modifier.padding(15.dp),verticalArrangement=Arrangement.spacedBy(5.dp)){Text("🐾 "+entry.first,fontWeight=FontWeight.Bold);Text(entry.second)}}}
 }
 }else if(page=="新手指南"){
 val tips=listOf("先維持生存" to "優先收集木板、塑膠與棕櫚葉，準備淨水器與烤架。","擴大木筏" to "在木筏外圍設置收集網，定期整理收集到的物資。","研究材料" to "製作研究桌，投入新材料逐步解鎖製作項目。","探索島嶼" to "靠近島嶼後固定木筏，探索陸地與水下並留意補給。","建立農場" to "使用種植箱種植作物，準備草地區照顧可飼養動物。","尋找故事地點" to "製作接收器與天線，依螢幕訊號探索故事地點。","管理資源" to "將材料分類存放，保留木板作為燃料與修理用途。")
 LazyColumn(verticalArrangement=Arrangement.spacedBy(9.dp),contentPadding=PaddingValues(bottom=12.dp)){items(tips){tip->Card(Modifier.fillMaxWidth()){Column(Modifier.padding(15.dp)){Text(tip.first,fontWeight=FontWeight.Bold);Text(tip.second)}}}}
 }else{
 LazyColumn(verticalArrangement=Arrangement.spacedBy(9.dp),contentPadding=PaddingValues(bottom=12.dp)){items(islands){island->Card(Modifier.fillMaxWidth()){Column(Modifier.padding(15.dp),verticalArrangement=Arrangement.spacedBy(5.dp)){Text("🏝️ "+island.first,fontWeight=FontWeight.Bold);Text(island.second)}}}}
 }
 }
 }
 }
}
