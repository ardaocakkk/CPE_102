
package hw1_sample_solution;

import java.util.Random;


public class Die {
    private int faceValue;
    private static final int FACE_MAX=6;
    public Die()
    {
        
    }   
    public Die(Die d)
    {
        this.faceValue=d.faceValue;
    }

    public int getFaceValue() {
        return faceValue;
    }

    public void setFaceValue(int faceValue) {
        this.faceValue = faceValue;
    }
    public void roll()
    {
        Random r=new Random();
        this.faceValue=1+r.nextInt(FACE_MAX);
        
    }

    @Override
    public String toString() {
        return "Die{" + "faceValue = " + faceValue + '}';
    }

    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Die other = (Die) obj;
        return this.faceValue == other.faceValue;
    }
    
}
