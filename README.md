# RAFT 指南

繁體中文 Raft Android 離線指南專案。

## 已加入的應用功能
- 一般模式／創造模式切換介面
- 物品百科：中英文名稱、分類、用途、取得方式與配方提示
- 中文、英文與用途搜尋
- 分類篩選與收藏
- 料理百科頁
- 動物飼養簡介
- 新手生存指南
- 故事島嶼與探索地點簡介
- 深色主題、手機底部導覽
- GitHub Actions APK 建置流程

## 資料範圍與重要限制
Raft Wiki 的 Items 條目記載遊戲共有 201 項物品，並分成食物與飲水、其他、工具、武器、裝備、資源、導航、裝飾及創造模式專用項目等類別。本專案目前整理的是常見非戰鬥物品與設備，不宣稱已收錄全部 201 項。武器相關內容不納入本指南。

目前部分配方只提供製作方向或提示，沒有逐項核對所有材料數量；料理頁提供料理名稱與用途提示，實際材料組合請依遊戲內找到的食譜卡片。部分裝飾品、藍圖、創造模式專用雜項與所有收集品仍未完整整理。

物品清單目前以文字與表情符號作為圖示，尚未加入每項物品的正式遊戲圖片。一般／創造模式切換介面已存在，但兩種模式的物品資料尚未完整分流。

## 資料參考
- Raft Wiki - Items: https://raft.fandom.com/wiki/Items
- Raft Wiki - Crafting Menu: https://raft.fandom.com/wiki/Crafting_Menu
- Raft Wiki - Recipes: https://raft.fandom.com/wiki/Recipes
- Raft Wiki - Research Table: https://raft.fandom.com/wiki/Research_Table
- Raft Wiki - Tools: https://raft.fandom.com/wiki/Tools

## APK
GitHub Actions 會在推送程式碼後嘗試建置 Debug APK。到 Actions 頁面開啟最新的 **Build Android APK** 執行紀錄；若該次工作流程成功，下載 **RAFT-GUIDE-debug-apk** Artifact。

依使用者要求，本次未進行人工安裝或操作測試；因此不保證 APK 已在實機安裝執行。
