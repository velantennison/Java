import java.util.ArrayList;
import java.util.List;
public class ArgePS {
    private boolean checkPerfectSquare(double x)
    {
        double sq = Math.sqrt(x);
        return ((sq - Math.floor(sq)) == 0);
    }
    private void solution(int current, int size, List<Integer> ar, boolean check[]){
        if(current==size)
        {
            System.out.println(ar);
            return;
        }
        for (int i = 1; i <=size; i++) {
            if (ar.size() == 0) {
                check[i] = true;
                ar.add(i);
                solution(current+1,size, ar, check);
                ar.remove(ar.size()-1);
                check[i] = false;
            }
            else if(check[i]==false)
            {
                int val=ar.get(ar.size()-1);
                if(checkPerfectSquare(val+i))
                {
                    check[i]=true;
                    ar.add(i);
                    solution(current+1,size, ar, check);
                    ar.remove(ar.size()-1);
                    check[i]=false;
                }
            }
        }
    }
    public static void main(String[] args){
        ArgePS ArgePS = new ArgePS();
        ArgePS.solution(0,17, new ArrayList<>(),new boolean[18]);
    }
}