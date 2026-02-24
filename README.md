**Description**

ImageMangler is an image processing program. Its purpose is to offer artists a fast and computer-light way to process images in unique ways. It is good for producing album covers and textures, among other things. An average person could use it, for example, for making artistic photo collages.

ImageMangler utilizes boolean algebra and bit shifting for image processing. The formation of artifacts resulting from bit shifting offers a new way to change the image's brightness more unevenly and artistically. The main feature of the program, however, is the unusual processing of image color values with their comparisons and other mathematical operations.


**Class Description**

ImageTool

An abstract class that contains functions used for changing image data types, resizing, and importing. The functions used for data type and size changes are from StackOverFlow users Kevin Bähre and Ocracoke. This is because Java's native functions for the same operations would take significantly more time, which is relevant in such a fast-paced program.

BufferedImage resize(BufferedImage, int, int)  
Redefines the size of the BufferedImage based on the int parameters and returns it.

Image getImage(BufferedImage)  
Converts the given BufferedImage to a JavaFX Image and returns it.

BufferedImage pickImage((File))  
Opens the file browser and returns the image the user selects from it. In the overloaded version, the method opens the directory given by the parameter by default.

FileTool

An abstract class that contains functions used for searching directories from the file browser, finding files, and saving and reading user data.

String Find(String)  
Searches the resources folder for a file and returns its path as a string.

Boolean Save(BufferedImage)  
Saves the BufferedImage to the project folder as a PNG. The name is always “img”+a random integer 0-9999. If saving the file fails, it returns false, otherwise true.

File pickDirectory()  
Opens the file browser and returns the directory selected by the user as a File data type.

void writeSave(saveFile)  
Serializes and saves the saveFile to the project folder named “ImgMng.mangle”.

saveFile readSave()  
Attempts to read the file “ImgMng.mangle” from the project folder, deserializes it, and returns it.

saveFile

A user data class that uses the Serializable class. Contains information about when the program was last opened and the default directory.

File defaultDir  
Default directory. Getters and setters.

String saveDate  
Date of when the software was last opened. Getter.

saveFile((saveFile)|(File))  
A constructor that either takes a File as an argument, which is saved as the default directory, or a saveFile, in which case the constructor saves the data of the given saveFile to the created object and saves the file to the hard drive with FileTool.writeSave.

setSaveData(save)  
Saves the data of the given saveFile object to the owning saveFile object and saves the data to the hard drive with FileToll.writeSave.

PixelProcessor

An abstract class used for image modification. The main principle is just to perform the selected modification on every pixel or compare/perform a mathematical calculation on the pixels of two images.

Int colorClamp(int color)  
Restricts the int value between -1 and 256 and returns it.

BufferedImage changeAll(BufferedImage, double, int, int)  
Adds a double to the color values of the given BufferedImage's pixels. Bitshifts int1 times left and int2 times right.

Returns the BufferedImage after all operations.

BufferedImage imgSubstract(BufferedImage, BufferedImage)  
Iterates through the color values of BufferedImage1's pixels and subtracts the color values of BufferedImage2's pixels at the same location from them. Returns BufferedImage1.

BufferedImage imgAdd(BufferedImage, BufferedImage)  
Iterates through the color values of BufferedImage1's pixels and adds the color values of BufferedImage2's pixels at the same location to them. Returns BufferedImage1.

BufferedImage imgAnd(BufferedImage, BufferedImage)  
Iterates through the color values of BufferedImage1's pixels and compares their bits with the bits of BufferedImage2's pixel color values using the AND(&) operator and returns the result.

BufferedImage imgXor(BufferedImage, BufferedImage)  
Iterates through the color values of BufferedImage1's pixels and compares their bits with the bits of BufferedImage2's pixel color values using the XOR(^) operator and returns the result.

BufferedImage invert(BufferedImage)  
Subtracts the color values of the given BufferedImage's pixels from 255 and returns the result.

ImageMangler

The main class where the program's user interface and event-specific methods for the buttons are defined.

Image img  
The JavaFX version of bufimg, which is shown in mainview. The so-called main image.

BufferedImage bufimg  
The image that is the software's output and is modified in the software.

ImageView mainview  
Shows img.

Slider bitshift  
Defines how many times the execute button shifts the bits of img to the left.

Slider bitshiftright  
Defines how many times the execute button shifts the bits of img to the right.

saveFile save  
User data saving object.

int canvasSizeX  
Defines the width to which all images are defined.

Int canvasSizeY  
Defines the height to which all images are defined.

EventHandler<ActionEvent> importImg(VBox)  
Opens the file browser and changes the image in mainview to the selected image. If defaultDir exists, it opens it by default. Makes the VBox invisible.

EventHandler<ActionEvent> setDefaultDirectory()  
Opens the file browser and sets the selected directory as the default directory.

EventHandler<ActionEvent> addition()  
Opens the file browser and uses PixelProcessor.imgAdd with the current image and the selected image as arguments. Changes the main image to the result. If defaultDir exists, it opens it by default.

EventHandler<ActionEvent> subtraction()  
Opens the file browser and uses PixelProcessor.imgSubstract with the current image and the selected image as arguments. Changes the main image to the result. If defaultDir exists, it opens it by default.

EventHandler<ActionEvent> and()  
Opens the file browser and uses PixelProcessor.imgAnd with the current image and the selected image as arguments. Changes the main image to the result. If defaultDir exists, it opens it by default.

EventHandler<ActionEvent> xor()  
Opens the file browser and uses PixelProcessor.imgXor with the current image and the selected image as arguments. Changes the main image to the result. If defaultDir exists, it opens it by default.

EventHandler<ActionEvent> invert()  
Uses PixelProcessor.invert on the current image and changes the main image to the result.

EventHandler<ActionEvent> updateImage()  
Uses PixelProcessor.changeAll with the arguments bufimg, the value of brightness, the value of bitshift converted to an integer, and the value of bitshiftRight converted to an integer. Sets the result as the main image.

EventHandler<ActionEvent> exportImage()  
Saves bufimg to the hard drive with FileTool.Save.

**Project Development and Possible Future Development**

The initial development of ImageMangler was laborious. First, I studied how Java handles images and what is the optimal way to convert different image data types to each other so that I could use the most easily modifiable yet efficient data type. Most of the time in user interface development was spent on producing standard code, which always needs to be produced a lot with JavaFX.

During the project's development, I noticed how useful libraries Java has for image processing. If I had started doing the same project with C++, it would have taken probably three times as long, if not more. I felt myself becoming friends with Java and JavaFX during the production.

I got my idea for this kind of image processing from FM synthesis, where one sound wave is subtracted from another sound wave. I then extended this idea to bit operations, because playing with bits is quite nice, and addition and subtraction are already widely used in other image processing tools.

The most entertaining part of the production was inventing different ways to modify images within the constraints produced by the program's structures. The future development would probably widely build on different image modification tools such as noise reduction, image comparison based on different procedural algorithms, or a free mode where the image sizes can be decided by oneself. Future development could possibly also be outsourced by making the project open source and promoting the project.

ImageMangler's most significant downside is the options that appear when the program is opened, which may confuse people.

In my opinion, ImageMangler is, overall, a functional and simple implementation of the combination of functional and simple ideas from different other fields.
