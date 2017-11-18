import json, requests

class Commit:
    def __init__(self, date, author, message, url):
        self.date = date
        self.author = author
        self.message = message
        self.url = url


class GitHubRequester:
    branches_example = "https://api.github.com/repos/PolyProgrammist/HackatonsMaster/branches"
    commits_example = "https://api.github.com/repos/PolyProgrammist/HackatonsMaster/commits?per_page=10&sha=dbcf1776ce96f6968dfdfb1008400bbaedd8ef1d"
    owner_example = "PolyProgrammist"
    repo_example = "HackatonsMaster"

    apilink = "https://api.github.com/repos/"
    branches_adding = "branches"
    magic_const = 10
    commits_adding = "commits?per_page=" + str(10) + "&sha="

    def __init__(self, owner, repo):
        self.prelink = self.apilink + owner + '/' + repo + '/'
        self.commit_shas = set()
        self.branches_shas = set()

    def get_new_commits(self):
        shas = self._get_all_new_shas()
        _commits = [self._get_new_commits_from_branch(s) for s in shas if s not in self.branches_shas]
        commits = []
        for commit_list in _commits:
            commits += commit_list
        self.branches_shas.update(shas)
        self.commit_shas.update([commit['sha'] for commit in commits])
        return [Commit(
            commit['commit']['author']['date'],
            commit['commit']['author']['name'],
            commit['commit']['message'],
            commit['html_url'],
        )for commit in commits]

    def print_commits(self):
        t = self.get_new_commits()
        for commit in t:
            print(commit.author + ' ' + commit.date + ' ' + commit.message + ' ' + commit.url)

    def _get_all_new_shas(self):
        q = requests.get(self.prelink + self.branches_adding)
        q = json.loads(q.text)
        res = [branch['commit']['sha'] for branch in q]
        return res

    def _get_new_commits_from_branch(self, sha):
        q = requests.get(self.prelink + self.commits_adding + sha)
        q = json.loads(q.text)
        res = [commit for commit in q if commit['sha'] not in self.commit_shas]
        return res

def qequs():
    ghr = GitHubRequester(GitHubRequester.owner_example, GitHubRequester.repo_example)
    ghr.print_commits()
    a = input()
    ghr.print_commits()

qequs()