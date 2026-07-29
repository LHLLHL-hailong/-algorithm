/**
 * 基本没什么难度 2 ASCII-->50
 */

class Solution {

    int[] nums=new int[]{2,3,4,5,6,7,8,9};
    String[] strs=new String []{"abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};




    List<String>list=new ArrayList<>();

    void dfs(StringBuilder sb,int levelnow ,HashMap<Integer,char[]>map,char [] now ){

        if(levelnow>=now.length){
            list.add(sb.toString());
            return ;
        }


        char[] cc=map.get(now[levelnow]-48);

        //System.out.println(cc.length);

        int len=sb.length();

        for( char c :cc){

            sb.append(String.valueOf(c));

            dfs(sb,levelnow+1,map,now);

            sb.setLength(len);

        }

    }


    public List<String> letterCombinations(String digits) {

        HashMap<Integer,char[]>map=new HashMap<>();

        for(int i=0;i<8;i++){
            map.put(nums[i],strs[i].toCharArray());

          //  System.out.println(nums[i]);

            


        }

        char [] now=digits.toCharArray();

        dfs(new StringBuilder(),0,map,now);

        return list;




        
    }
}