



package load_file;


public class arrayQuickSort {
    
    public static void quickSort(String array[]){
        doQuickSort(array, 0, array.length-1);
    }
    
    private static void doQuickSort(String array[], int start, int end){
        int pivot_point;

        if (start<end){
            pivot_point=partition(array, start, end);
            doQuickSort(array, start, pivot_point-1);
            doQuickSort(array, pivot_point+1, end);
        }
    }
    
    private static int partition(String array[], int start, int end){
        String pivot_value;
        int end_of_left_list, mid;
        
        mid=(start+end)/2;
        swap(array, start, mid);
        pivot_value=array[start];
        end_of_left_list=start;
        
        for (int scan=start+1; scan<=end; scan++){
            if (array[scan].compareTo(pivot_value)<0){
                end_of_left_list++;
                swap(array, end_of_left_list, scan);
            }
        }
        swap(array, start, end_of_left_list);
        return end_of_left_list;
    }
    
    private static void swap(String[] array, int a, int b){
        String temp;
        
        temp=array[a];
        array[a]=array[b];
        array[b]=temp;
    }

}
