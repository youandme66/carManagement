function FrameSplitor(frameSetID,frameIndex,dispType,reduceType,minSize)
{
  var DISPTYPE_HORIZONTAL=1;
  var DISPTYPE_VERTICAL=2;

  var YESNO_YES=1;
  var YESNO_NO=0;

  var REDUCE_TYPE_NONE=0;
  var REDUCE_TYPE_PRE=1;
  var REDUCE_TYPE_POST=2;

  //����
  this.showFlag=YESNO_YES;
  this.frameSetID=frameSetID;
  this.frameIndex=frameIndex;
  this.dispType=dispType;
  this.reduceType=reduceType;
  this.minSize=minSize;
  this.isLockMove=false;

  var startScreenX=0;
  var startScreenY=0;
  var frameSetObj=null;

  var origSizeArr=null;
  var isStarArr=null;
  var preSizeArr=null;
  var curSizeArr=null;

  //����


  this.init=function()
  {
    frameSetObj=window.parent.document.getElementById(this.frameSetID);
    var counter=0;
    origSizeArr=this.getSizeArr();
    preSizeArr=origSizeArr;
    var rowsCols="";
    if(this.dispType==DISPTYPE_HORIZONTAL)
      rowsCols=frameSetObj.cols;
    else
      rowsCols=frameSetObj.rows;
    var arr=rowsCols.split(",");
    isStarArr=new Array();
    var hasStar=false;
    for(var i=0;i<arr.length;i++)
    {
      if(!hasStar && arr[i].indexOf("*")!=-1)
      {
        isStarArr[i]=true;
        hasStar=true;
      }
      else
        isStarArr[i]=false;
      if(i==arr.length-1 && !hasStar)
        isStarArr[i]=true;
    }
  }

  //ȡframe�Ŀ�Ȼ�߶ȼ���
  this.getSizeArr=function()
  {
    var counter=0;
    var sizeArr=new Array();
    var children=frameSetObj.childNodes;
    for(var i=0;i<children.length;i++)
    {
      if(!children[i].tagName)//�еĽڵ������TextNode,��tagName
        continue;
      var tagName=children[i].tagName.toLowerCase();
      if(tagName!="frame" && tagName!="frameset")
        continue;
      if(this.dispType==DISPTYPE_HORIZONTAL)
        sizeArr[counter]=children[i].offsetWidth;
      else
        sizeArr[counter]=children[i].offsetHeight;
      counter++;
    }
    return sizeArr;
  }

  //�ص�����
  this.showCallback=function(){}//��ʾʱ�ص�
  this.hideCallback=function(){}//����ʱ�ص�
  this.moveCallback=function(){}//�ƶ�ʱ�ص�

  //��ʾ/����
  this.showHide=function(flag)
  {
    if(flag!=null && this.showFlag==flag)
      return;
    if(this.reduceType==REDUCE_TYPE_NONE)
      return;
    var imgObj=document.getElementById("mover");
    curSizeArr=this.getSizeArr();
    var imgSrc="";
    if(this.showFlag==YESNO_YES)//��ǰ��ʾ
    {
      var addSize=0;
      if(this.reduceType==REDUCE_TYPE_PRE)
        addSize=this.minSize-curSizeArr[this.frameIndex-1];
      else
        addSize=curSizeArr[this.frameIndex+1]-this.minSize;
      if(this.dispType==DISPTYPE_HORIZONTAL)
      {
        frameSetObj.cols=this.getCurRowsCols(addSize);
        if(this.reduceType==REDUCE_TYPE_PRE)
          imgSrc="images/rightMove.gif";
        else
          imgSrc="images/leftMove.gif";
      }
      else
      {
        frameSetObj.rows=this.getCurRowsCols(addSize);
        if(this.reduceType==REDUCE_TYPE_PRE)
          imgSrc="images/downMove.gif";
        else
          imgSrc="images/upMove.gif";
      }
      preSizeArr=curSizeArr;
      this.showFlag=YESNO_NO;       
      this.hideCallback();
    }
    else
    {
      var addSize=0;
      if(this.reduceType==REDUCE_TYPE_PRE)
        addSize=preSizeArr[this.frameIndex-1]-curSizeArr[this.frameIndex-1];//this.minSize;
      else if(this.reduceType==REDUCE_TYPE_POST)
        addSize=curSizeArr[this.frameIndex+1]-preSizeArr[this.frameIndex+1];
      if(this.dispType==DISPTYPE_HORIZONTAL)
      {
        frameSetObj.cols=this.getCurRowsCols(addSize);
        if(this.reduceType==REDUCE_TYPE_PRE)
          imgSrc="images/leftMove.gif";
        else
          imgSrc="images/rightMove.gif";
      }
      else
      {
        frameSetObj.rows=this.getCurRowsCols(addSize);
        if(this.reduceType==REDUCE_TYPE_PRE)
          imgSrc="images/upMove.gif";
        else
          imgSrc="images/downMove.gif";
      }
      this.showFlag=YESNO_YES;
      this.showCallback();
    }
    imgObj.src=imgSrc;
  }

  this.mouseDown=function(ev)
  {
    ev || (ev=window.event);//firefox�б��봫����������
    startScreenX=ev.screenX;
    startScreenY=ev.screenY;
    curSizeArr=this.getSizeArr();
  }
   
  this.mouseUp=function(ev)
  {
    startScreenX=0;
    startScreenY=0;
  }

  this.mouseMove=function(ev)
  {
    if(this.isLockMove)
      return;
    ev || (ev=window.event);//firefox�б��봫����������
    if(Constants.isIE && ev.button!=1)//firefox�����������꣬������onmousemove�¼�. �Ҹ��¼�event.button=0
      return;
    if(this.dispType==DISPTYPE_HORIZONTAL)
    {
      if(startScreenX>0)
      {
        frameSetObj.cols=this.getCurRowsCols(ev.screenX-startScreenX);
        this.moveCallback();
      }
    }
    else
    {
      if(startScreenY>0)
      {
        frameSetObj.rows=this.getCurRowsCols(ev.screenY-startScreenY);
        this.moveCallback();
      }
    }    
  }

  //ȡ��ǰ��rows��colsֵ��
  this.getCurRowsCols=function(addSize)
  {
    var rowsCols="";
    for(var i=0;i<curSizeArr.length;i++)
    {
      
      if(i>0)
        rowsCols+=",";
      if(this.frameIndex-1==i)
      {
        if(isStarArr[i])
          rowsCols+="*";
        else
          rowsCols+=curSizeArr[i]+addSize;
        rowsCols+=","+curSizeArr[i+1]+",";
        if(isStarArr[i+2])
          rowsCols+="*";
        else
          rowsCols+=curSizeArr[i+2]-addSize;
        i+=2;
        continue;
      }
      if(isStarArr[i])
        rowsCols+="*";
      else
        rowsCols+=curSizeArr[i];
    }
    return rowsCols;
  }

}
