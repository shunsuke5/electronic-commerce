import { isExpireToken, isValidToken } from "../../common.js";

const isTokenValid = isValidToken("/customer/auth");
if (await isTokenValid) {
    console.log("商品表示");
    // 商品一覧を表示する
}