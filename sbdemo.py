#!/usr/bin/env python


import argparse
import git
import os
import glob
import subprocess
from os.path import expanduser
import xml.etree.ElementTree as xml
from distutils.version import StrictVersion

home = expanduser('~')
git_dir = home + '/git/sbdemo'
pom = git_dir + '/pom.xml'
ns = '{http://maven.apache.org/POM/4.0.0}'
buildcmd = '/usr/bin/mvn -f ' + pom + ' install -DskipTests'

xml.register_namespace('', 'http://maven.apache.org/POM/4.0.0')
xml.register_namespace('xsi', 'http://www.w3.org/2001/XMLSchema-instance')

parser = argparse.ArgumentParser(description='Process release.')
parser.add_argument("--release", type=StrictVersion, required=True)
release = parser.parse_args().release
build_dir = home + '/.m2/repository/com/java2016/sbdemo/art/'

ibranch = 'release/' + str(release)

repo = git.Repo( git_dir )


if ibranch in repo.git.branch():
    print ibranch + ' branch already exists'
    repo.git.checkout('pom.xml')
    repo.git.checkout(ibranch)
    print 'Active branch ' + repo.active_branch.name
else:
    print 'creating ' + ibranch
    repo.git.checkout('pom.xml')
    repo.git.checkout('HEAD', b=ibranch)
    print 'updating pom, project version to -> ' + str(release) + '-SNAPSHOT'
    epom = xml.parse(pom)
    epom.find(ns + 'version').text = str(release) + '-SNAPSHOT'
    epom.write(pom, encoding="utf-8", xml_declaration=True)
    repo.git.commit('-a', m="committing pom.xml " + str(release) + '-SNAPSHOT')
    print 'Active branch ' + repo.active_branch.name


if glob.glob(build_dir + '/' + str(release) + '*'):
    build = os.walk(build_dir).next()[1]
    build.sort(key=lambda s: map(int, s.split('.'))) # <--- minor bug of sorting here, not real case - only when create lower than last release

    last_build = int(''.join(build[-1]).rsplit('.', 1)[1])
    new_build = str(release) + '.' + str((last_build + 1))
else:
    new_build = str(release) + '.0'


print 'updating pom, build version to -> ' + str(new_build)
epom = xml.parse(pom)
epom.find(ns + 'version').text = str(new_build)
epom.write(pom, encoding="utf-8", xml_declaration=True)

print 'Building ....'
p = subprocess.Popen(buildcmd, shell=True, stdout = subprocess.PIPE)

stdout, stderr = p.communicate()

if p.returncode == 0:
    print 'Build completed with exit code ' + str(p.returncode)
    print 'Attaching tag ' + 'V' + str(new_build)
    repo.git.tag('V' + str(new_build))
    print 'pushing to origin by tag -> V' + str(new_build)
    print 'repo.git.push("origin", "V" + str(new_build))'

else:
    print 'Build failed with exit code ' + str(p.returncode)






