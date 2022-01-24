var backContextPath = '/boardrestBack';
var frontContextPath;
var loginedId = "id1";

$(function() {
	let href = location.href; //현재사용중인 주소URL값 ex: http://localhost:8888/boardresfFront
	console.log("href=" + href);
	console.log("location.host=" + location.host); //localhost:8888
	let index = href.indexOf(location.host) + location.host.length;
	console.log("index=" + index);
	frontContextPath = href.substring(index, href.indexOf("/", index + 1));//href.substinrg()
	console.log("frontContextPath=" + frontContextPath);
});