export  class HelpUtils{

 public isEmpty(obj:any):boolean {
        for(var key in obj) {
            if(obj.hasOwnProperty(key))
                return false;
        }
        return true;
    }
}