
const pathnames = ['/pages/questionnaire/index.html']

const handleHeaderLoad = () => {
  if (pathnames.includes(location.pathname)) {
    $('#handerFallback').remove()
    $('#headerDivB').remove()
  }  
}

const onMyProject = () => {
  if (location.pathname !== '/pages/questionnaire/index.html')
  location.href = "/pages/questionnaire/index.html"
}

const onToUser = () => {
  console.log(location.pathname, 'location.pathname');
  if (location.pathname !== '/pages/user/index.html')
    location.href = "/pages/user/index.html"
}

const onSignOut = () => {
  $util.clearItem()
  location.href = "/pages/login/index.html"
}


function formatDateTime(dateTimeString) {
  const dateTime = new Date(dateTimeString);

  const year = dateTime.getFullYear();
  const month = dateTime.getMonth() + 1;
  const day = dateTime.getDate();

  const hours = dateTime.getHours();
  const minutes = dateTime.getMinutes();
  const seconds = dateTime.getSeconds();

  const formattedDateTime = `${year}-${month.toString().padStart(2, '0')}-${day.toString().padStart(2, '0')} ${hours.toString().padStart(2, '0')}:${minutes.toString().padStart(2, '0')}:${seconds.toString().padStart(2, '0')}`;

  return formattedDateTime;
}

function getUrlParams(url) {
  const params = {};
  const paramRegex = /[?&]+([^=&]+)=([^&]*)/gi;

  let match;
  while ((match = paramRegex.exec(url))) {
    const key = decodeURIComponent(match[1]);
    const value = decodeURIComponent(match[2]);
    params[key] = value;
  }

  return params;
}