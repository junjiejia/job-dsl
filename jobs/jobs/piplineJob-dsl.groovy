pipelinejob('piplineJob-dsl') {
  description('Helper that simply populates the seed job since the seed cant rewrite itself.')
  wrappers {
    preBuildCleanup()
  }
 definition {
      cpsScm {
        scm {
          git {
            remote {
              name('origin')
              url('https://github.com/junjiejia/job-dsl')
            }
            branch('master')
            extensions {
              cleanAfterCheckout()
              disableRemotePoll()
            }
          }
        }
        // Many keep their pipeline in the root of the project, but it can be anywhere
        scriptPath('Jenkinsfile')
      }
  steps {
    gradle 'clean test'
    dsl {
      external 'jobs/seed.groovy'
    }
  }
}
