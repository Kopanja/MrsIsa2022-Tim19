const TokenService = {

    getLocalAccessToken: function(){
        let userJson = localStorage.getItem("user");
        if(userJson){
            const user : any = JSON.parse(userJson);
            return user.accessToken;
        }
        return null;
        
    },

    getUser: function(){
        let userJson = localStorage.getItem("user");
        if(userJson){
            const user : any = JSON.parse(userJson);
            return user.user;
        }
        return null;
    },

    setUser: function(user:any) {
        console.log(JSON.stringify(user));
        localStorage.setItem("user", JSON.stringify(user));
    },
    removeUser() {
        localStorage.removeItem("user");
      }
}

export default TokenService;