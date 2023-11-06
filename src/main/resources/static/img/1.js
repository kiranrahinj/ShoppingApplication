function addToCart(pid, pname, price) {

	cart = localStorage.getItem("cart");
	let pcart = JSON.parse(cart);

	if (cart == null) {
		let products = [];
		let product = {
			productId: pid, productName: pname, productQuantity: 1, productPrice: price
		}
		products.push(product);

		localStorage.setItem("cart", JSON.stringify(products));
	}
	else {
		

		let oldProd = pcart.find((item) => item.productId == pid);

		if (oldProd != null) {
			oldProd.productQuantity = oldProd.productQuantity + 1;
			pcart.map((item) => {
				if (item.productId == oldProd.pid) {
					item.productQuantity = oldProd.productQuantity;
					console.log(item.productQuantity);
				}
			})
		}
		else {
			let product = {
				productId: pid, productName: pname, productQuantity: 1, productPrice: price
			}
			pcart.push(product);
		}
		localStorage.setItem("cart", JSON.stringify(pcart));
	}
	updateCart();
	
}

function updateCart() {

	let cart = localStorage.getItem("cart");
	let pcart = JSON.parse(cart);
	
	let table = `
    <table class="table">
        <thead>
             <tr>
                <th scope="col">Product Name</th>
                <th scope="col">Quantity</th>
                <th scope="col">Price</th>
                <th scope="col">Action</th>
            </tr>
        </thead>
   
        <tbody>`;
	
	let totalPrice=0;
	pcart.forEach((item) => {
		totalPrice+=item.productPrice * item.productQuantity;
		table += `
            <tr>
                <td>${item.productName}</td>
                <td>${item.productQuantity}</td>
                <td>${item.productPrice * item.productQuantity}</td>
                <td><button class=" btn bg-danger" onclick="removeProd('${item.productId}')">Remove</button></td>
            </tr>
            `
	});

	table += `
			<tr>
      			<td colspan="4" class="text-right"><b>Total Price : ${totalPrice}</b></td>
      		</tr>
        </tbody>   
    </table>`;

	$(".cartBody").html(table);
	$(".val").html(pcart.length);

}


function removeProd(pId) {
	console.log("clicked");
	let cart = localStorage.getItem("cart");
	let pcart = JSON.parse(cart);
	let newArray=pcart.filter((item) => item.productId != pId);

	localStorage.setItem("cart", JSON.stringify(newArray));

	updateCart();
}


// when we have to run the function
$(document).ready(function() {
	updateCart()
})