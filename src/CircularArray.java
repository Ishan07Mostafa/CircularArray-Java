public class CircularArray{
  
  private int start;
  private int size;
  private Object[] circular;
  
  /*
   * if Object [] lin = {10, 20, 30, 40, null}
   * then, CircularArray(lin, 2, 4) will generate
   * Object [] cir = {40, null, 10, 20, 30}
   */
  public CircularArray(Object [] linear, int start, int size){
    this.start = start;
    this.size = size;
    circular = new Object[linear.length];
    int j = this.start;
    for(int i = 0; i<linear.length; i++){
      this.circular[j%circular.length] = linear[i];
      j++;
    }
  }
  
  //Prints from index --> 0 to cir.length-1
  public void printFullLinear(){
    for(int i=0; i<this.circular.length; i++){
      if(i==this.circular.length-1){
        System.out.print(this.circular[i]+".");
      }
      else{
        System.out.print(this.circular[i]+", ");
      }
    }
    System.out.println();
  }
  
  // Starts Printing from index start. Prints a total of size elements
  public void printForward(){
    for(int i = this.start; i<this.start+this.size; i++){
      if(i==this.start+this.size-1%this.circular.length){
        System.out.print(this.circular[i%this.circular.length]+".");
      }
      else{
        System.out.print(this.circular[i%this.circular.length]+", ");
      }
    }
    System.out.println();
  }
  
  
  public void printBackward(){
    for(int i = this.start+this.size-1%this.circular.length; i>=this.start; i--){
      if(i<0){
        i=this.circular.length-1;
      }
      if(i==this.start){
        System.out.print(this.circular[i%this.circular.length]+".");
      }
      else{
        System.out.print(this.circular[i%this.circular.length]+", ");
      }
    }
    System.out.println();
  }
  
  // With no null cells
  public void linearize(){
    Object[] linearArray = new Object[this.size];
    int j = this.start;
    for(int i = 0; i<this.size; i++){
      linearArray[i] = this.circular[j%this.circular.length];
      j++;
    }
    this.circular = linearArray;
  }
  
  // Do not change the Start index
  public void resizeStartUnchanged(int newCapacity){
    Object[] resizedArray = new Object[newCapacity];
    int i = this.start;
    int j = this.start;
    for(int k = 0; k<this.size; k++){
      resizedArray[i%resizedArray.length] = this.circular[j%this.circular.length];
      i+=1;
      j+=1;
    }
    this.circular = resizedArray;
  }
  
  // Start index becomes zero
  public void resizeByLinearize(int newCapacity){
    Object[] resizedArray = new Object[newCapacity];
    int j = start;
    for(int i = 0; i<size; i++){
      resizedArray[i] = this.circular[j%this.circular.length];
      j++;
    }
    this.circular = resizedArray;
  }
  
  /* pos --> position relative to start. Valid range of pos--> 0 to size.
   * Increase array length by 3 if size==cir.length
   * use resizeStartUnchanged() for resizing.
   */
  public void insertByRightShift(Object element, int position){
    if(this.size==this.circular.length) resizeStartUnchanged(this.size+3);
    for(int i = start+size+1; i>=start+position; i--){
      this.circular[i%this.circular.length] = this.circular[(i-1)%this.circular.length];
    }
    this.circular[start+position] = element;
    this.size++;
  }
  
  public void insertByLeftShift(Object element, int position){
    if(this.size==this.circular.length) resizeStartUnchanged(this.size+3);
    for(int i = 0; i<start+position; i++){
      this.circular[i%this.circular.length] = this.circular[(i+1)%this.circular.length];
    }
    this.circular[start+position] = element;
    this.size++;
    this.start--;
  }
  
  /* parameter--> pos. pos --> position relative to start.
   * Valid range of pos--> 0 to size-1
   */
  public void removeByLeftShift(int position){
    this.circular[(start+position)%this.circular.length] = this.circular[(start+position+1)%this.circular.length];
    this.circular[(start+position+1)%this.circular.length] = null;
    this.size--;
  }
  
  /* parameter--> pos. pos --> position relative to start.
   * Valid range of pos--> 0 to size-1
   */
  public void removeByRightShift(int position){
    for(int i = start+position; i>=start; i--){
      this.circular[i%this.circular.length] = this.circular[(i-1)%this.circular.length];
    }
    this.circular[start] = null;
    this.start++;
    this.size--;
  }
  
  
  //This method will check whether the array is palindrome or not
  public void palindromeCheck(){
    int i = start;
    int j = (start+size-1)%this.circular.length;
    int k;
    for(k = 0; k<size/2; k++){
      if(this.circular[i]!=this.circular[j]){
        System.out.println("This array is NOT a palindrome.");
        break;
      }
      i = (i+1)%this.circular.length;
      j--;
      if(j==-1) j = this.circular.length-1;
    }
    if(k==size/2) System.out.println("This array is a palindrome.");
  }

  //This method will check the given array across the base array and if they are equivalent in terms of values return true, or else return false
  public boolean equivalent(CircularArray k){
    if(this.size!=k.size) return false;
    int i = this.start;
    int j = k.start;
    for(int l = 0; l<this.size; l++){
      if(k.circular[j]!=this.circular[i]) return false;
      i = (i+1)%this.circular.length;
      j = (j+1)%k.circular.length;
    }
    return true;
  }
}