/*
 *鍒濆鍖栭儴鍒嗛〉闈㈡暟鎹�
 */
var viewHeight;//娴忚鍣ㄧ獥鍙ｅ彲瑙嗛珮搴�
var viewWidth;//娴忚鍣ㄧ獥鍙ｅ彲瑙嗗搴�
var pageHeight;//鏂囨。椤甸潰楂樺害
var pageWidth;//鏂囨。椤甸潰瀹藉害


$(document).ready(function () {
	// DOM缁樺埗瀹屾瘯鍗冲紑濮嬭繍琛岋紝涓嶇瓑鍒帮紝鍥剧墖锛屾枃瀛楃殑鍏冪礌鐨勫姞杞�
	//window鏄湪鎵�湁鍏冪礌鍧囧姞杞藉畬鎴愬悗鎵嶅紑濮嬭繍琛岋紝娉ㄦ剰鍖哄埆
	 //alert(viewWidth+'/'+viewHeight+'/'+pageWidth+'/'+pageHeight)

	
})


window.onload = function(){
	//window鏄湪鎵�湁鍏冪礌鍧囧姞杞藉畬鎴愬悗鎵嶅紑濮嬭繍琛岋紝娉ㄦ剰鍖哄埆
    //寤惰繜鍔犺浇,鐢ㄤ簬澶勭悊閮ㄥ垎鍥燚OM鍔犺浇鑰楁椂杈冮暱寮曡捣鐨凚UG
    var t_load = setTimeout(function(){
    	
    	//鍒锋柊閮ㄥ垎鍏ㄥ眬鏁版嵁
		getValue();
		//杩涜椤甸潰璋冩暣
		pageAdjust();

    },300);//delayTime
    
}


$(window).resize(function(){
	//鍒锋柊閮ㄥ垎鍏ㄥ眬鏁版嵁
	getValue();
	//杩涜椤甸潰璋冩暣
	pageAdjust();

})




function getValue()
{
	/*鍏ㄥ眬鍙橀噺
	 * 娴忚鍣ㄧ獥鍙ｅ彲瑙嗛珮搴�
	 */
	viewHeight = $(window).height();

	/*鍏ㄥ眬鍙橀噺
	 * 娴忚鍣ㄧ獥鍙ｅ彲瑙嗗搴�
	 */
	 viewWidth = $(window).width();
	//鑾峰彇閮ㄥ垎鍏ㄥ眬鍙橀噺
	/*鍏ㄥ眬鍙橀噺
	 * 鏂囨。楂樺害
	 */
	 pageHeight = $(document).height();

	 /*鍏ㄥ眬鍙橀噺
	 * 鏂囨。瀹藉害
	 */
	 pageWidth = $(document).width();


}

function pageAdjust()
{
	//椤甸潰鏍峰紡鐨勮皟鏁�
	//鍦ㄥ垵濮嬪寲鍜岄〉闈㈠昂瀵稿彉鍖栨椂锛屼粠getValue()璋冪敤
	/*
	 *nav_list鐨勮皟鏁�
	 */
	var width_header_menu_list = $('#header').width() - 416;
	$('#header_menu_list').width(width_header_menu_list);
	var left_header_menu_list  = (width_header_menu_list*0.28-12)/2+208;
	$('#header_menu_list').css('marginLeft', left_header_menu_list);

	//section_button_list浣嶇疆璋冩暣
	console.log(viewHeight/2-50) 
    $("#section_button").css('top', viewHeight/2-50 + "px");

	 for (var i = 0; i <6; i++) {
	 	$('.header_detail_container').eq(i).css('left', 1000*i+'px');
	 }
    //璋冩暣鑳屾櫙

    $('#bg').height(viewHeight);
    $('.bg').height(viewHeight);
}


/*
 *header_nav_list涓婄Щ鍔╩enu_detail绉诲姩鐗规晥
 *
 */
(function(){
	var open_status = false;//true琛ㄧず鎵撳紑鐘舵�
	var run_status =false;//鍔ㄧ敾杩愯鐘舵�锛宼rue琛ㄧず姝ｅ湪杩愯
	var run1_status = false;//nav鐨勫姩鐢荤姸鎬�
$('.header_menu_list').on('mouseenter',function(){
	/*绉诲姩鍒癶eader_nav_list涓婃椂锛�
	 *鍏堝垽鏂璵enu_detail鏈夋病鏈夋墦寮�紝濡傛灉鎵撳紑灏辩洿鎺ヨ繘琛屽乏鍙冲垏鎹紝濡傛灉娌℃湁鎵撳紑鍒欏厛杩涜宸﹀彸鍒囨崲鍐嶆墦寮�
	 *
	 */
    if(open_status)
    {
    	//鎵撳紑鐘舵�
    	if(!run_status)
	    {
	    	run_status = true;//姝ｅ湪杩愯
	    	var index = $(this).index();
	    	$('#header_menu_details_mask').animate({
	    		left: -1000*index+'px'}, 300,function(){
	    			run_status = false;//缁撴潫杩愯鐘舵�
	    		})
	    }
    } else {
    	//鍏抽棴鐘舵�
	    var index = $(this).index();
	    $('#header_menu_details_mask').css('left', -1000*index+'px');
	    if(!run1_status)
	    {
		    $('#header_menu_details').slideDown();
		    run1_status = true;
		    //鎵撳紑瀹屾瘯锛屾爣璁扮姸鎬�
		    open_status = true;
		}
    }
})

$('#header_nav').on('mouseleave',function(){
	//榧犳爣绂诲紑nav鍖哄煙锛屽叧闂環eader_menu_details
	$('#header_menu_details').slideUp(300,function(){
		run1_status = false;
	});
	//鍏抽棴瀹屾瘯锛屾爣璁扮姸鎬�
	open_status = false;
})
})();//涓嶄細鑷姩鎻愬崌

/*
 *鎼滅储妗嗘晥鏋�
 *
 */
$('#header_search_text').on('focus',function(){
	$(this).css('color', 'black');
	var value = $(this).val();
 	console.log(value);
 	if( value == '杈撳叆鎼滅储鍐呭')
 	{
 		$(this).val('');
 	} 
}).on('blur',function(){
	$(this).css('color', '#8e8e8e');
	var value = $(this).val();
 	if( value == '')
 	{
 		$(this).val('杈撳叆鎼滅储鍐呭');
 	}
})

/*
 *鑳屾櫙鍒囨崲
 */
var val = {
        fa_id:"bg",
        son_class:"bg",
        speed:7000,
        interval:10000,
    };
    banner(val);
function banner(opts)
{
    //杈撳叆鍊间负鐖剁骇鐨処D浠ュ強鏈�ぇ瀛愬厓绱犵殑绫诲悕
    console.log(opts);
    console.log(opts.fa_id);
    var banner = document.getElementById(opts.fa_id);//鑾峰彇褰撳墠banner鐨勫璞�
    var banner_img = $("#"+opts.fa_id).children("."+opts.son_class);//鑾峰彇鎵�湁瀛愬厓绱犵殑瀵硅薄
    var img_num = banner_img.length;//鑾峰彇褰撳墠banner鐨勫浘鐗囧紶鏁�
    var speed = opts.speed;//娓愬彉鎸佺画鏃堕棿锛屽崟浣嶏細姣
    var interval = opts.interval;//闂撮殧鏃堕棿
    var currentImg = 0;//褰撳墠鏄剧ず鐨勫浘鐗囷紝鍒濆鍖栨椂淇濈暀鏄剧ず绗竴寮犲浘鐗囥�
    init();//璋冪敤鍒濆鍖栧嚱鏁帮紱

    function init()
    {
        //鍒濆鍖�
        console.log(img_num);
        //閮ㄥ垎鍙橀噺榛樿鍊煎垵濮嬪寲
        if(!speed){speed = 1000; console.log(speed);}
        if(!interval){interval = 3000; console.log(interval);}
        if(speed<50){speed = 50; alert("閫熷害鏃堕棿闂撮殧涓嶅緱灏忎簬50ms");}
        if(speed>interval){interval = 2*speed;alert('鍙樺寲鏃堕棿涓嶅緱澶т簬鍙樺寲闂撮殧');}


        for(i=1; i<img_num;i++)
        {
            //闅愯棌澶氫綑鐨勫浘鐗�
            banner_img.eq(i).hide();    
        }
        t1 = setTimeout(function(){picChange();},3000);
    }

    function picChange()
    {
        //鑷姩杞崲
        banner_img.eq(currentImg).fadeOut(speed);
        banner_img.eq((currentImg + 1)%img_num).fadeIn(speed);
        currentImg = (currentImg + 1)%img_num;
        t2 = setTimeout(function(){picChange();},interval);
    }
}
