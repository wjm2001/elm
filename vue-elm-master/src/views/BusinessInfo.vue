<template>
  <div class="wrapper">
    <!-- header部分 -->
    <header>
      <p>商家信息</p>
    </header>


    <!-- 商家logo部分 -->
    <div class="business-logo">
      <!-- <img src="img/sj01.png"/> -->
      <img :src="require('../assets/'+business.businessImg+'.png')">
    </div>

    <!-- 商家信息部分 -->
    <div class="business-info">
      <h1>{{business.businessName}}</h1>
      <p>&#165;{{business.starPrice}}起送 &#165;{{business.deliveryPrice}}配送</p>
      <p>{{business.businessExplain}}</p>
    </div>

    <!-- 食品列表部分 -->
    <ul class="food">
      <li v-for="(item,index) in foodArr">
        <div class="food-left">
          <img :src="require('../assets/'+item.foodImg+'.png')">
          <div class="food-left-info">
            <h3>{{item.foodName}}</h3>
            <p>{{item.foodExplain}}</p>
            <p>&#165;{{item.foodPrice}}</p>
          </div>
        </div>
        <div class="food-right">
          <!-- 为了保证有3块，布局能对齐，用div装i标签 -->
          <div>
            <i class="fa fa-minus-circle" @click="minus(index)" v-show="item.quantity!=0"></i>
          </div>
          <p><span v-show="item.quantity!=0">{{item.quantity}}</span></p>
          <div>
            <i class="fa fa-plus-circle" @click="add(index)"></i>
          </div>
        </div>
      </li>

    </ul>

    <!-- 购物车部分 -->
    <div class="cart">
      <div class="cart-left">
        <div class="cart-left-icon" :style="totalQuantity==0?'background-color:#505051':'background-color:#31901'">
          <i class="fa fa-shopping-cart"></i>
          <div class="cart-left-icon-quantity" v-show="totalQuantity!=0">{{totalQuantity}}</div>
        </div>
        <div class="cart-left-info">
          <p>&#165;{{totalPrice}}</p>
          <p>另需配送费{{business.deliveryPrice}}元</p>
        </div>
      </div>
      <div class="cart-right">
        <!-- 显示二选一： -->
        <!-- 做的时候可以先注释掉一个 -->
        <!-- 不够起送费 -->
        <div class="cart-right-item" v-show="totalPrice<business.starPrice"
          style="background-color: #535356;cursor: default;">
          &#165;{{business.starPrice}}起送
        </div>
        <!-- 达到起送费，可以跳转结账 -->
        <div class="cart-right-item" @click="toOder" v-show="totalPrice>=business.starPrice">
          去结算
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'BusinessInfo',
  data() {
    return {
      businessId: this.$route.query.businessId,
      business: {},
      foodArr: [],
      user: {}
    }
  },
  created() {
    this.user = this.$getSessionStorage('user');

    // 根据businessId查询商家信息
    // this.$axios.post('BusinessController/getBusinessById', this.$qs.stringify({
    // 	businessId: this.businessId
    // })).then(responsee => {
    this.$axios.get('business/findBusinessByBusinessId', {
      params: {
        businessId: this.businessId
      }
    }).then(response => {
      this.business = response.data;
    }).catch(error => {
     // console.error(error);
    });

    // 根据businessId查询所属食品信息
    // this.$axios.post('FoodController/listFoodByBusinessId', this.$qs.stringify({
    // 	businessId: this.businessId
    // })).then(responsee => {
    this.$axios.get('food/findFoodsByBusinessId', {
      params: {
        businessId: this.businessId
      }
    }).then(response => {
      this.foodArr = response.data;
      for (let i = 0; i < this.foodArr.length; i++) {
        this.foodArr[i].quantity = 0;
      }

      // 如果已登录,需要查询购物车中食品选购记录
      if (this.user != null) {
        this.listCart();
      }
    }).catch(error => {
      //console.error(error);
    });
  },
  methods: {
    listCart() {
      // this.$axios.post('CartController/listCart', this.$qs.stringify({
      // 	businessId: this.businessId,
      // 	userId: this.user.userId,
      // 	// foodId: this.foodArr[index].foodId
      // })).then(responsee => {
      this.$axios.get('cart/listCart', {
        params: {
          userId: this.user.id,
          businessId: this.businessId,
        }
      }).then(response => {
        let cartArr = response.data;
        // 遍历所有食品列表
        for (let foodItem of this.foodArr) {
          foodItem.quantity = 0;
          for (let cartItem of cartArr) {
            if (cartItem.foodId == foodItem.foodId) {
              foodItem.quantity = cartItem.quantity;
            }
          }
        }
        this.foodArr.sort();
      }).catch(error => {
        //console.error(error);
      });
    },
    add(index) {
      // 首先登录验证
      if (this.user == null) {
        this.$router.push({
          path: '/login'
        });
        return;
      }

      if (this.foodArr[index].quantity == 0) {
        // 做insert
        this.saveCart(index);
      } else {
        // 做update
        this.updateCart(index, 1);
      }
    },
    minus(index) {
      // 登录验证，其实没有必要
      if (this.user == null) {
        this.$router.push({
          path: '/login'
        });
        return;
      }

      if (this.foodArr[index].quantity > 1) {
        // 做update
        this.updateCart(index, -1);
      } else {
        // 做delete
        this.removeCart(index);
      }
    },
    // 老师最开始写成savaCart了
    saveCart(index) {
      // this.$axios.post('CartController/saveCart', this.$qs.stringify({
      // 	businessId: this.businessId,
      // 	userId: this.user.userId,
      // 	foodId: this.foodArr[index].foodId
      // })).then(responsee => {
      this.$axios.post('cart/saveCart',
        this.$qs.stringify({
          businessId: this.businessId,
          foodId: this.foodArr[index].foodId,
          userId: this.user.id
        })).then(response => {
          if (response.data != '') {
            // 数据库插入成功
            // 此食品数量更新为1
            this.foodArr[index].quantity = 1;
            // vue监听不到,数组变化
            // 调用vue遍历方法,vue就可监听到之前数组的变化,不是想真正排序
            this.foodArr.sort();
          } else {
            alert('向购物车中添加食品失败！');
          }
        }).catch(error => {
          //console.error(error);
        });
    },
    updateCart(index, num) {
      // this.$axios.post('CartController/updateCart', this.$qs.stringify({
      // 	businessId: this.businessId,
      // 	userId: this.user.userId,
      // 	foodId: this.foodArr[index].foodId,
      // 	quantity: this.foodArr[index].quantity + num
      // })).then(responsee => {
      this.$axios.post('cart/updateCart',
        this.$qs.stringify({
          userId: this.user.id,
          businessId: this.businessId,
          foodId: this.foodArr[index].foodId,
          quantity: this.foodArr[index].quantity + num
        })).then(response => {
          if (response.data.length >= 1) {
            // 数据库更新成功
            // 食品数量更新1或-1
            this.foodArr[index].quantity += num;
            this.foodArr.sort();
          } else {
            alert(response.data);
            alert('向购物车中更新食品数量失败！')
          }
        }).catch(error => {
          //console.error(error);
        });
    },
    removeCart(index) {
      // this.$axios.post('CartController/removeCart', this.$qs.stringify({
      // 	businessId: this.businessId,
      // 	userId: this.user.userId,
      // 	foodId: this.foodArr[index].foodId,
      // 	// quantity: this.foodArr[index].quantity+num
      // })).then(responsee => {
      this.$axios.post('cart/removeCart',
        this.$qs.stringify(
          {
            userId: this.user.id,
            businessId: this.businessId,
            foodId: this.foodArr[index].foodId
      })).then(response => {
        if (response.data != null) {
          // 数据库删除成功
          // 此食品数量更新为0，视图的减号和数量消失
          this.foodArr[index].quantity = 0;
          this.foodArr.sort();
        } else {
          alert('从购物车中删除食品失败！')
        }
      }).catch(error => {
        //console.error(error);
      });
    },
    toOder() {
      this.$router.push({
        path: '/orders',
        query: {
          businessId: this.business.businessId
        }
      });
    }
  },
  computed: {
    // 食品总价
    totalPrice() {
      let total = 0;
      for (let item of this.foodArr) {
        total += item.foodPrice * item.quantity;
      }
      return total.toFixed(2);
    },
    // 食品总数量
    totalQuantity() {
      let quantity = 0;
      for (let item of this.foodArr) {
        quantity += item.quantity;
      }
      return quantity;
    },
    // 食品结算总价格
    totalSettle() {
      return this.totalPrice + this.business.deliveryPrice;
    }
  }
}
</script>

<style scoped>
/* 总容器 */
.wrapper {
  width: 100%;
  height: 100%;
}

/* header部分 */
.wrapper header {
  width: 100%;
  height: 12vw;
  background-color: #0097ff;
  color: #fff;
  font-size: 4.8vw;

  position: fixed;
  left: 0;
  top: 0;
  z-index: 1000;

  display: flex;
  justify-content: center;
  align-items: center;
}

/* 商家logo部分 */
.wrapper .business-logo {
  width: 100%;
  height: 35vw;
  margin-top: 12vw;

  display: flex;
  justify-content: center;
  align-items: center;
}

.wrapper .business-logo img {
  width: 40vw;
  height: 30vw;
  border-radius: 5px;
}

/* 商家信息 */
.wrapper .business-info {
  width: 100%;
  height: 20vw;

  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.wrapper .business-info h1 {
  font-size: 5vw;
}

.wrapper .business-info p {
  font-size: 3vw;
  color: #666;
  margin-top: 1vw;
}

/* 食品列表部分 */
.wrapper .food {
  width: 100%;
  /* margin-bottom: 14vw; */
  padding-bottom: 14vw;
}

.wrapper .food li {
  width: 100%;
  box-sizing: border-box;
  padding: 2.5vw;
  user-select: none;

  display: flex;
  justify-content: space-between;
  align-items: center;
}

.wrapper .food li .food-left {
  display: flex;
  align-items: center;
}

.wrapper .food li .food-left img {
  width: 20vw;
  height: 20vw;
}

.wrapper .food li .food-left .food-left-info {
  margin-left: 3vw;
}

.wrapper .food li .food-left .food-left-info h3 {
  font-size: 3.8vw;
  color: #555;
}

.wrapper .food li .food-left .food-left-info p {
  font-size: 3vw;
  color: #888;
  margin-top: 2vw;
}

.wrapper .food li .food-right {
  width: 16vw;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.wrapper .food li .food-right .fa-minus-circle {
  font-size: 5.5vw;
  color: #999;
  cursor: pointer;
}

.wrapper .food li .food-right p {
  font-size: 3.6vw;
  color: #333;
}

.wrapper .food li .food-right .fa-plus-circle {
  font-size: 5.5vw;
  color: #0097ef;
  cursor: pointer;
}

/* 购物车部分 */
.wrapper .cart {
  width: 100%;
  height: 14vw;

  position: fixed;
  left: 0;
  bottom: 0;

  display: flex;
}

.wrapper .cart .cart-left {
  flex: 2;
  background-color: #505051;

  display: flex;
}

.wrapper .cart .cart-left .cart-left-icon {
  width: 16vw;
  height: 16vw;
  box-sizing: border-box;
  border: solid 1.6vw #444;
  border-radius: 8vw;
  background-color: #3190e8;
  font-size: 7vw;
  color: #fff;

  display: flex;
  justify-content: center;
  align-items: center;

  margin-top: -4vw;
  margin-left: 3vw;

  /* 相对定位,小红点的父容器 */
  position: relative;
}

.wrapper .cart .cart-left .cart-left-icon-quantity {
  width: 5vw;
  height: 5vw;
  border-radius: 2.5vw;
  background-color: red;
  color: #fff;
  font-size: 3.6vw;

  display: flex;
  justify-content: center;
  align-items: center;

  position: absolute;
  right: -1.5vw;
  top: -1.5vw;
}

/* 伪类选择器,可以选择一组相同元素的第一个或最后一个元素 */
.wrapper .cart .cart-left .cart-left-info p:first-child {
  font-size: 4.5vw;
  color: #fff;
  margin-top: 1vw;
}

.wrapper .cart .cart-left .cart-left-info p:last-child {
  font-size: 2.8vw;
  color: #aaa;
}

.wrapper .cart .cart-right {
  flex: 1;
  /* 达到，去结算 */
  /* background-color: #38ca73; */
}

/* 达到起送费时的样式 */
.wrapper .cart .cart-right .cart-right-item {
  width: 100%;
  height: 100%;
  background-color: #38ca73;
  color: #fff;
  font-size: 4.5vw;
  font-weight: 700;
  user-select: none;
  cursor: pointer;

  display: flex;
  justify-content: center;
  align-items: center;
}

/* 未到起送费 */
/* .wrapper .cart .cart-right .cart-right-item{
		width: 100%;
		height: 100%;
		background-color: #535356;
		color: #fff;
		font-size: 4.5vw;
		font-weight: 700;
		user-select: none;

		display: flex;
		justify-content: center;
		align-items: center;
	} */
</style>
