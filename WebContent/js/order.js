			function modifyReceiver(){
				var newReceiverName = document.getElementById('newReceiverName');
				var newReceiverPhone = document.getElementById('newReceiverPhone');
				var newReceiverAddress = document.getElementById('newReceiverAddress');
				var newReceiverPostcode = document.getElementById('newReceiverPostcode');
				alert(newReceiverAddress.value);
				alert(newReceiverPostcode.value);
				document.getElementById('receiverName').value = newReceiverName.value;
				document.getElementById('receiverName1').value = newReceiverName.value;
				document.getElementById('receiverMobilePhone').value = newReceiverPhone.value;
				document.getElementById('receiverAddress').value = newReceiverAddress.value;
				document.getElementById('receiverPostCode').value = newReceiverPostcode.value;
				
				document.getElementById('receiverInfo1').innerHTML= 
				'<p>收货人：'+newReceiverName.value+'</p>'+
				'<p>手机：'+newReceiverPhone.value+'</p>'+
				'<p>地址：'+newReceiverAddress.value+'</p>'+
				'<p>地址：'+newReceiverPostcode.value+'</p>';

			}
			function selectProduct(element){
				var product = document.getElementById(element.id);
				document.getElementById('productInfo').innerHTML =
					"商品一级分类："+product.cells[1].innerHTML+"\n"+
					"商品二级分类："+product.cells[2].innerHTML+"\n"+
					"商品名字："+product.cells[3].innerHTML+"\n"+
					"商品剩余数量："+product.cells[4].innerHTML+"\n"+
					"商品备注："+product.cells[5].innerHTML+"\n";
				document.getElementById('productId').value = product.cells[0].innerHTML;
			}
			function selectNewOrder(element){
				var newOrder= document.getElementById(element.id);
				document.getElementById('newOrderId').value = newOrder.cells[0].innerHTML;
			}