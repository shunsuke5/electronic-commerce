import { isValidToken } from "../../common.js";

const isTokenValid = await isValidToken("/admin/auth");
if (isTokenValid) {
    console.log("商品登録");
    // 商品一覧を表示する
}