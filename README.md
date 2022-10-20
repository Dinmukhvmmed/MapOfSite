# MapOfSite
Консольное приложение, формирующая карту новостного сайта Tengrinews в многопоточном режиме, и записывает ее в файл. 
Используется ForkJoinPool для рекурсии. Дочерние сслыки в файле распологаются с отступом на одну табуляцию от родительских ссылок.
Пример записи:
https://tengrinews.kz/
	https://tengrinews.kz/accidents/
		https://tengrinews.kz/around_sports/mayk-tayson-raskryil-neojidannyie-podrobnosti-draki-samolete-480891/
		https://tengrinews.kz/around_sports/motivatsiya-trenirovok-zastavit-hodit-sportzal-480785/
		https://tengrinews.kz/around_sports/opublikovana-burnaya-reaktsiya-yunyih-futbolistov-pobedu-480821/
	https://tengrinews.kz/article/
		https://tengrinews.kz/article/moi-roditeli-bejali-vremya-golodomora-kazahstana-istorii-1825/
		https://tengrinews.kz/article/ohota-chinovnikov-trillion-tenge-mafiya-intervyu-glavoy-1856/
		https://tengrinews.kz/article/pokupaem-pervichku-v-astane-kak-podobrat-idealnuyu-kvartiru-1859/
		https://tengrinews.kz/article/zdes-vseh-uvajayut-kazahov-reportaj-kazahskogo-rayona-1827/
