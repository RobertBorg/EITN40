if (Meteor.isClient) {

  Template.haOne.events({
    'click div.haOne>div.bOne>a.btn' : function () {
       var cc = $('#creditCardNumberIn').val().trim();
       var ccArr = cc.split('');
       var sum = 0;
       for(var i = ccArr.length - 1; i >= 0 ; i--){
        if((ccArr.length - 1 - i) % 2 == 0){
          var temp = ccArr[i] * 2;
          temp = temp.toString().split('');
          for(var j = 0; j < temp.length ; j++){
            sum += parseInt(temp[j]);
          }
        } else {
          sum += parseInt(ccArr[i]);
        }
       }
       sum *= 9 ;
       sum %= 10; 
       $('#luhnLastDidgit').val(sum);
    }
  });

   Template.haOne.events({
    'click div.haOne>div.cOne>div.partOne>a.btn' : function () {
      var x=Math.floor(1+Math.random()*11);
      var r=Math.floor(1+Math.random()*11);
      $('#cOneR').val(r);
      $('#cOneX').val(x);
    },
    'click div.haOne>div.cOne>div.partTwo>a.btn' : function () {
      var B = Math.pow(parseInt($('#cOneR').val()), parseInt($('#cOneBankPublicKey').val()));
      var hashOfX = calcMD5($('#cOneX').val());
      B = (B * hashOfX) % 3 // 3 is n, part of public modulus
      $('#cOneToSendToBank').val(B);
     // console.log("asd");
    }
  });
}

if (Meteor.isServer) {
  Meteor.startup(function () {
    // code to run on server at startup
  });
}
