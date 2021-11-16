import { WechatyBuilder } from 'wechaty';
import qrTerm from "qrcode-terminal";
import axios from "axios";
import md5 from "md5";
import { createRequire } from "module";
const require = createRequire(import.meta.url);

// QR code
function onScan(qrcode, status) {
  console.log("onScan");
  post();
  // qrTerm.generate(qrcode, { small: true });

  // const qrcodeImageUrl = [
  //   "https://wechaty.js.org/qrcode/",
  //   encodeURIComponent(qrcode),
  // ].join("");

  // console.log(qrcodeImageUrl);
}

// Login
async function onLogin(user) {
  console.log(`${user.name()} login`);
}

// Logout
function onLogout(user) {
  console.log(`${user.name()} logouted`);
}

// Error
function onError(e) {
  console.error("Bot error:", e);
}

// Message
function onMessage(msg) {
  console.log("onMessage");
  // notify me if the message is not
  // from myself and from a chatting room
  if(!msg.self() && msg.room() == null){
    post();
  }
  console.log("post Message");
}

// Axios post
async function post() {
  try{
    console.log("post start");
    let appKey = "";
    let appSecret = "";
    let serverUrl = "";
    
    let data = {
      "data": {
        "data": {
          "action": "xyz.theprojecttool.messenger.wechatPush"
          },
      }
  }

    let result = await axios({
      headers: {
        "Accept": "application/json ",
        "Content-Type": "application/json",
        "User-Agent": "LeanCloud-Java-SDK/8.0.1 ",
        "X-LC-Id": appKey,
        "X-LC-Prod": "1",
        "X-LC-Key": appSecret,
      },
      method: "post",
      url: serverUrl,
      data: data
    });
    console.log(result.data);
    console.log("post end.")
    // return ;
  } catch(error){
    console.log(error.response.data);
    console.log("post error.");
    return ;
  }
}

const bot = WechatyBuilder.build({
  name: "my-bot",
  puppet: 'wechaty-puppet-wechat',
})

bot.on("scan", onScan);
bot.on("login", onLogin);
bot.on("logout", onLogout);
bot.on("error", onError);
bot.on("message", onMessage);

bot.start()
  .then(() => console.log("log in"))
  .catch(async (e) => {
    console.error("Bot start() fail:", e);
    await bot.stop();
    process.exit(-1);
  });
