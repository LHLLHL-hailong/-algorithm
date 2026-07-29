
/**
 * 这题唯一需要注意的就是char数组也是可以排序的，而且这样建立键更快
 */
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {

        HashMap<String, ArrayList<String >>map=new HashMap<>();


        for(String str:strs){

            char[] cc=str.toCharArray();

            Arrays.sort(cc);

            String key=new String(cc);


            ArrayList<String> cnt=null;
            cnt=map.get(key);
            if(cnt==null){
                cnt=new ArrayList<String>();
                map.put(key,cnt);
            }

            cnt.add(str);
        }

        List<List<String>> res=new ArrayList<>();
        for(var tt:map.entrySet()){
            res.add(tt.getValue());
        }

        return res;
    }
}