import { isValidToken } from "../../common.js";

const isTokenValid = await isValidToken("/admin/auth");
if (isTokenValid) {
    console.log("カテゴリ登録");
    // 商品一覧を表示する
}