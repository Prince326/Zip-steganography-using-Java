# Zip-steganography-using-Java
A Zip archive consists of local file headers, local files, and at the end of the Zip file, the central directory. When a zip application like WinZip or FilZip opens an archive, it first reads the directory. Only when you actually extract a file, it reads the offset from its directory entry, and then the local file is read and uncompressed. Something that is not listed in the central directory will not be listed in the Zip application.
Zip archives can contain lots of single files, each of them having two sizes: compressed size and uncompressed. Have you ever calculated the expected archive size from the compressed file sizes and compared it to the size of the Zip file? No? Well, that's why a few additional bytes - additional compressed text files - won't be found by chance.

Zip files
This Zip file is clean, and every zipped file has an entry in the central directory:

1.local file header
2.central directory

What we need
There are only three steps on the way to partly invisible Zip archives. We need to:

1.read and write zip files in general,
2.write Zip entries without adding them to the central directory,
3.find those Zip entries in the archive.
