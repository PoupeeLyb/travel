import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex);

//公共state的状态
const state={
    user:{
        id: 0,
        username:'',
        avatarUrl:'',
    }
}

//唯一取值的方法
const getters={
    getUser(state){
        return state.user;
    }
}

//唯一改变state.user的方法，同步阻塞
const mutations={
    updateUser(state,user){
      state.user=user;
    }
}

//异步调用mutation方法
const actions={
    asyncUpdateUser(context,user){
        context.commit('updateUser',user);
    }
}

export default new Vuex.Store({
    state,
    getters,
    mutations,
    actions
})