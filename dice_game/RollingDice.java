
package hw1_sample_solution;

public class RollingDice {
    public static void main(String[] args) {
        // TODO code application logic here
        int times=5;
        Die d=new Die();
        for(int i=0;i<times;++i)
        {
            
            d.roll();
            System.out.println("Roll #"+(i+1)+": "+d);
            
        }
        Die d1=new Die();
        d1.roll();
        System.out.println("d1: " +d1);
        d1.setFaceValue(3);
        System.out.println("d1: " +d1);
        Die d2=new Die(d1);
        //d2.roll();
        System.out.println("d2: " +d2);
        System.out.println("d1==d2: "+d1.equals(d2));
        
            
        
            
        
        
        
    }
}
