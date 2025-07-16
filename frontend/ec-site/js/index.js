import { isExpireToken } from "../../common.js";

// トークンの確認
if (isExpireToken("/customer/auth")) {
    console.log("expire");
    window.location.href = "login.html"
} else {
    // 商品一覧を取得して表示する
    console.log("else");
}