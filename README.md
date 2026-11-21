# RAFT 指南

繁體中文 Raft Android 離線指南專案。

## 已加入的應用功能
- 一般模式／創造模式切換介面
- 擴充物品百科：中英文名稱、分類、用途、取得方式與配方提示
- 補入更多食物、家具、裝飾、裝備與創造模式雜項
- 創造模式會額外顯示創造模式專屬種子、收藏與裝飾項目
- 料理百科擴充至料理鍋與榨汁機的常見完整菜單名稱
- 內建「AI問答」頁：以離線百科資料搜尋物品、料理、動物、藍圖、島嶼與新手玩法
- 中文、英文與用途搜尋
- 分類篩選與收藏
- 料理百科頁
- 動物飼養簡介
- 新手生存指南
- 故事島嶼與探索地點簡介
- 深色主題、手機底部導覽
- GitHub Actions APK 建置流程

## 內建 AI 助手說明
目前「AI問答」是完全離線的知識庫檢索助手，會根據 App 已收錄的物品、料理、動物、藍圖、島嶼與新手資料組合回答；遇到資料庫沒有的問題會提示資料不足。它不是連線大型語言模型，也不能保證回答所有自由提問。若要真正的生成式 AI，需要安全的伺服器端 API 整合與金鑰管理，不能把 API 金鑰直接寫進 APK。

## 資料範圍與重要限制
Raft Wiki 的 Items 條目記載遊戲共有 201 項物品，並分成食物與飲水、其他、工具、武器、裝備、資源、導航、裝飾及創造模式專用項目等類別。本專案持續補齊非戰鬥物品、食物、設備、裝飾、裝備與創造模式雜項；不收錄武器與爆裂物內容，因此不會以遊戲 Wiki 的 201 項總數宣稱本專案已逐項收錄全部內容。

目前資料尚未達到逐項全部核對完成。部分配方只提供製作方向或提示，沒有逐項核對所有材料數量；料理頁提供料理名稱與用途提示，實際材料組合請依遊戲內找到的食譜卡片。部分裝飾品、藍圖、創造模式專用雜項、環境條目與所有收集品仍未完整整理。

物品清單目前以文字與表情符號作為圖示，尚未加入每項物品的正式遊戲圖片，圖示目前以表情符號呈現。一般／創造模式已依目前整理的資料進行顯示篩選；創造模式專屬項目會在創造模式顯示。仍有部分裝飾變體、收集品、藍圖與環境資料待逐項核對。

## 資料參考
- Raft Wiki - Items: https://raft.fandom.com/wiki/Items
- Raft Wiki - Crafting Menu: https://raft.fandom.com/wiki/Crafting_Menu
- Raft Wiki - Recipes: https://raft.fandom.com/wiki/Recipes
- Raft Wiki - Research Table: https://raft.fandom.com/wiki/Research_Table
- Raft Wiki - Tools: https://raft.fandom.com/wiki/Tools

## APK
GitHub Actions 會在推送程式碼後嘗試建置 Debug APK。到 Actions 頁面開啟最新的 **Build Android APK** 執行紀錄；若該次工作流程成功，下載 **RAFT-GUIDE-debug-apk** Artifact。

依使用者要求，本次未進行人工安裝或操作測試；因此不保證 APK 已在實機安裝執行。


## 線上 Raft 專用 AI（後端初步建置）
- 已新增 `backend/worker.js`：Gemini 生成式 AI 後端，先判斷問題是否屬於 Raft，再回答；非 Raft 問題會被拒絕。
- 已加入 Raft 範圍限制、繁體中文回答、資料不足時不得捏造，以及非戰鬥內容範圍限制。
- 使用 Gemini Google 搜尋功能取得遊戲相關參考資料，若有搜尋來源會回傳來源連結。
- API 金鑰只允許存放在伺服器環境變數 `GEMINI_API_KEY`，絕不可寫入 Android APK 或提交到 GitHub。
- `backend/wrangler.toml` 是 Cloudflare Worker 部署設定。

**目前狀態：後端程式骨架已加入，但尚未部署，也尚未接到 Android App。** 要正式啟用，仍需由使用者與家長／監護人協助建立並設定 API 金鑰及雲端 Worker，之後再把 Worker 網址接入 App。請勿把 API 金鑰貼在聊天或公開 GitHub。
