function clearCookie(cookieName) {
  var exp = new Date();
  exp.setTime(exp.getTime() - 1);
  var cval = this.getCookie(cookieName);
  if (cval != null) {
    document.cookie = cookieName + "=" + cval + ";expires=" + exp.toGMTString();
  }
}

function setCookie(cookieName, value, expiremMinutes) {
  var exdate = new Date();
  exdate.setTime(exdate.getTime() + expiremMinutes * 60 * 1000);
  document.cookie = cookieName + "=" + escape(value) + ((expiremMinutes == null) ? "" : ";expires=" + exdate.toGMTString());
}

function getCookie(cookieName) {
  if (document.cookie.length > 0) {
    var c_start = document.cookie.indexOf(cookieName + "=");
    if (c_start != -1) {
      c_start = c_start + cookieName.length + 1;
      var c_end = document.cookie.indexOf(";", c_start);
      if (c_end == -1)
        c_end = document.cookie.length
      return unescape(document.cookie.substring(c_start, c_end))
    }
  }
  return ""
}