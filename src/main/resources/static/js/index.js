let accordion=document.querySelector('#accordion');
let backBtn=document.querySelector('#back');
window.onload=function(){
    let stuId=localStorage.getItem('stuId');
    let pwd=localStorage.getItem('pwd');
    let data=JSON.parse(localStorage.getItem('data'));
    if(data){
      this.console.log(data);
        let g='';
            data.map(item=>{
             g+=HTMLshow(item);
            })
        accordion.innerHTML=g;
    }else{
        window.location.href='../login.html';
    }
}
function HTMLshow(data){
  let s=`<div class="card">
  <div class="card-header" id="heading`+data.id+`">
    <h2 class="mb-0">
      <button class="btn btn-link collapsed" type="button" data-toggle="collapse" data-target="#collapse`+data.id+`" aria-expanded="false" aria-controls="collapse`+data.id+`">`
      +data.courseName+
      `</button>
    </h2>
  </div>
  <div id="collapse`+data.id+`" class="collapse" aria-labelledby="heading`+data.id+`" data-parent="#accordion">
    <div class="card-body">
    <p>任课教师&nbsp;:&nbsp;<span>`+data.teacherName+`</span></p>
    <p>课程代码&nbsp;:&nbsp;<span>`+data.courseCode+`</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学分&nbsp;:&nbsp;<span>`+data.credit+`</span></p>
    <p>上课时间&nbsp;:&nbsp;<span>`+data.time+`</span></p>
    <p>交流qq群&nbsp;:&nbsp;<span>`+data.qqGroup+`</span></p>
    <p>是否已上传课程资源（PPT、视频、题库、资料等）&nbsp;:&nbsp;<span>`+data.uploadCourseResources+`</span></p>
    <p>前三周拟采取的授课模式（直播、PPT速课、视频、PPT+线下讨论等）&nbsp;:&nbsp;<span>`+data.method+`</span></p>
    <p>备注（非校级平台建课的请注明其他平台网址，或注明其他线上教学新模式）&nbsp;:&nbsp;<span>`+data.note+`</span></p>
    </div>
  </div>
</div>`
    return s;
}
backBtn.addEventListener('click',function(){
  window.location.href='../login.html';
})
