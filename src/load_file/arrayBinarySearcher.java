



package load_file;


public class arrayBinarySearcher {
    
    public static int search(String[] array, String value){
        int first, last, middle, position;
        boolean found;
        
        first=0;
        last=array.length-1;
        position=-1;
        found=false;
        
        while (!found && first<=last){
            middle=(first+last)/2;
            if (array[middle].toUpperCase().equals(value)){
                found=true;
                position=middle;
            }
            else if (array[middle].compareTo(value)>0){
                last=middle-1;
            }
            else {
                first=middle+1;
            }
        }
        return position;
    }

}
