from PIL import Image
import sys


def resizeAndCrop(imgPath):
    img = Image.open(imgPath)
    resized_image = img.resize((int(sys.argv[1]), int(sys.argv[2])))

    resized_image = resized_image.convert("RGB")
    resized_image.save("resized_" + imgPath.replace("./", ""))

resizeAndCrop("./sonnenaufgang-am-morgen-tapete-3554x1999_53.jpg")